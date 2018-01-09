package com.jm.unicom.api.controller;

import com.jm.unicom.api.entity.ShopQrCode;
import com.jm.unicom.api.service.ShopQrCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * QrCodeController
 *
 * @author Eric
 * @date 2018/1/2
 */
@Slf4j
@RestController
@RequestMapping("/shopQrCode")
@Api(value = "/shopQrCode", description = "店铺二维码操作接口")
public class ShopQrCodeController {
    @Resource
    private ShopQrCodeService shopQrCodeService;

    @GetMapping("/get/{uuid}")
    @ApiOperation(value = "获取店铺二维码", notes = "获取店铺二维码", httpMethod = "GET")
    public ShopQrCode get(@ApiParam(name = "uuid", value = "店铺uuid", required = true) @PathVariable String uuid) {
        return shopQrCodeService.findByUuid(uuid);
    }

    @PostMapping("/add/{shopUuid}")
    @ApiOperation(value = "生成店铺二维码", notes = "生成店铺二维码", httpMethod = "POST")
    public ShopQrCode save(@ApiParam(name = "uuid", value = "店铺uuid", required = true) @PathVariable String shopUuid) throws IOException {
        return shopQrCodeService.save(shopUuid);
    }
}
