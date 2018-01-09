package com.jm.unicom.api.service.impl;

import com.jm.unicom.api.dao.CustomerDao;
import com.jm.unicom.api.entity.Customer;
import com.jm.unicom.api.service.CustomerService;
import com.jm.unicom.api.entity.Shop;
import com.jm.unicom.core.service.RedisService;
import com.jm.unicom.core.util.HttpRequestUtil;
import com.jm.unicom.core.util.MobileUtil;
import com.jm.unicom.core.util.ScanTypeUtil;
import com.jm.unicom.core.util.TimeUtil;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.api.customer.service.impl
 *          <br><b>Date:</b> 2018/1/5 17:19
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerDao customerDao;

    @Resource
    private RedisService redisService;

    @Override
    public Customer save(Customer customer, String shopUuid, HttpServletRequest request) throws Exception {
        String key = shopUuid + "," + HttpRequestUtil.getIpAddr(request);
        Elements phoneInfo = MobileUtil.getMobileFrom(customer.getCustomerPhone());
        customer.setPhoneOperator(MobileUtil.validateMobile(customer.getCustomerPhone()));
        customer.setScanType(ScanTypeUtil.isWechatOrAlipay(request));
        customer.setCustomerIp(HttpRequestUtil.getIpAddr(request));
        customer.setPhoneAddress(phoneInfo.get(1).text());
        customer.setPhoneType(phoneInfo.get(2).text());
        customer.setPhoneZone(phoneInfo.get(3).text());
        customer.setZipCode(phoneInfo.get(4).text().substring(0, 6));
        customer.setShop(new Shop(shopUuid));
        customer = customerDao.save(customer);
        redisService.set(key, customer, TimeUtil.todayOverTime());
        return customer;
    }

    @Override
    public Boolean isQualifications(String shopUuid, String prizeName, HttpServletRequest request) {
        Customer customer = new Customer();
        customer.setPrizeName(prizeName);
        String realIp = HttpRequestUtil.getIpAddr(request);
        String key = shopUuid + "," + realIp;
        if (!(redisService.exists(key))) {
            redisService.set(key, customer, TimeUtil.todayOverTime());
            return true;
        }
        return false;
    }

    @Override
    public Customer getPrizeName(String shopUuid, HttpServletRequest request) {
        String realIp = HttpRequestUtil.getIpAddr(request);
        String key = shopUuid + "," + realIp;
        return (Customer) redisService.get(key);
    }

    @Override
    public boolean isExistCustomer(String shopUuid, HttpServletRequest request) {
        String key = shopUuid + "," + HttpRequestUtil.getIpAddr(request);
        Customer customer = (Customer) redisService.get(key);
        return customer.getUuid() != null;
    }
}
