package com.jm.unicom.api.customer.service.impl;

import com.jm.unicom.api.customer.dao.CustomerDao;
import com.jm.unicom.api.customer.entity.Customer;
import com.jm.unicom.api.customer.service.CustomerService;
import com.jm.unicom.api.shop.entity.Shop;
import com.jm.unicom.core.util.HttpRequestUtil;
import com.jm.unicom.core.util.MobileUtil;
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

    @Override
    public Customer save(Customer customer, String shopUuid, HttpServletRequest request) throws Exception {
        Elements phoneInfo = MobileUtil.getMobileFrom(customer.getCustomerPhone());
        customer.setPhoneOperator(MobileUtil.validateMobile(customer.getCustomerPhone()));
        customer.setCustomerIp(HttpRequestUtil.getIpAddr(request));
        customer.setPhoneAddress(phoneInfo.get(1).text());
        customer.setPhoneType(phoneInfo.get(2).text());
        customer.setPhoneZone(phoneInfo.get(3).text());
        customer.setZipCode(phoneInfo.get(4).text().substring(0, 6));
        customer.setShop(new Shop(shopUuid));
        return customerDao.save(customer);
    }
}
