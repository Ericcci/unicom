package com.jm.unicom.api.controller;

import com.jm.unicom.api.entity.Customer;
import com.jm.unicom.api.service.CustomerService;
import com.jm.unicom.core.common.InfoData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value = "/customer", description = "顾客信息操作接口")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @PostMapping("/{shopUuid}")
    @ApiOperation(value = "保存顾客信息", notes = "保存顾客信息", httpMethod = "POST")
    public InfoData save(@ApiParam(name = "customer", value = "顾客实体类", required = true) @RequestBody Customer customer,
                         @ApiParam(name = "shopUuid", value = "店铺uuid", required = true) @PathVariable String shopUuid, HttpServletRequest request) throws Exception {
        return InfoData.success(customerService.save(customer, shopUuid, request), "保存成功");
    }

    @GetMapping("/isQualifications/{shopUuid}")
    @ApiOperation(value = "查看用户是否具备抽奖资格", notes = "查看用户是否具备抽奖资格", httpMethod = "GET", response = InfoData.class)
    public InfoData isQualifications(@ApiParam(name = "shopUuid", value = "店铺uuid", required = true) @PathVariable String shopUuid,
                                     @ApiParam(name = "prizeName", value = "奖品名称", required = true) @RequestParam String prizeName, HttpServletRequest request) {
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
