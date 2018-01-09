package com.jm.unicom.api.customer.controller;

import com.jm.unicom.api.customer.entity.Customer;
import com.jm.unicom.api.customer.service.CustomerService;
import com.jm.unicom.common.InfoData;
import com.jm.unicom.core.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.api.customer.controller
 *          <br><b>Date:</b> 2018/1/5 17:17
 */
@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @Resource
    private RedisService redisService;

    @PostMapping("/{shopUuid}")
    public InfoData save(@RequestBody Customer customer, @PathVariable String shopUuid, HttpServletRequest request) throws Exception {
        return InfoData.success(customerService.save(customer, shopUuid, request), "保存成功");
    }

    @GetMapping("/isQualifications/{shopUuid}")
    public InfoData isQualifications(@PathVariable String shopUuid, @RequestParam String prizeName, HttpServletRequest request) {
        if (customerService.isQualifications(shopUuid, prizeName, request)) {
            return InfoData.success("具备抽奖资格");
        } else {
            if ("谢谢惠顾".equals(customerService.getPrizeName(shopUuid, request).getPrizeName())) {
                return InfoData.fail("您与奖品擦身而过，请明日再来");
            }
            if (customerService.isExistCustomer(shopUuid, request)) {
                return InfoData.fail("您在该店铺已参与过抽奖，请明日再来");
            }
        }
        return InfoData.fail(customerService.getPrizeName(shopUuid, request), "已获取奖品");
    }
}
