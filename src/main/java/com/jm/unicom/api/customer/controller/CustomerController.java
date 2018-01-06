package com.jm.unicom.api.customer.controller;

import com.jm.unicom.api.customer.entity.Customer;
import com.jm.unicom.api.customer.service.CustomerService;
import com.jm.unicom.common.InfoData;
import com.jm.unicom.core.util.HttpRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 * <b>ProjectName:</b> unicom
 * <br><b>PackageName:</b> com.jm.unicom.api.customer.controller
 * <br><b>Date:</b> 2018/1/5 17:17
 */
@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @PostMapping("/{shopUuid}")
    public InfoData save(@RequestBody Customer customer, @PathVariable String shopUuid, HttpServletRequest request) throws Exception {
        return InfoData.success(customerService.save(customer, shopUuid, request), "保存成功");
    }

    @GetMapping("/isQualifications/{shopUuid}")
    public InfoData isQualifications(@PathVariable String shopUuid, HttpServletRequest request) {
//        if (customerService.isQualifications(shopUuid, request)){
//
//        }
        //return customerService.isQualifications(shopUuid, request)?InfoData.success("具备抽奖资格");
        String ip = HttpRequestUtil.getIpAddr(request);
        System.out.println(ip);
            return null;
    }
}
