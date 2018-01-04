package com.jm.unicom.shop.controller;

import com.jm.unicom.shop.entity.ShopQrCode;
import com.jm.unicom.shop.service.ShopQrCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
public class ShopQrCodeController {
    @Resource
    private ShopQrCodeService shopQrCodeService;

    @GetMapping("/{uuid}")
    public ShopQrCode get(@PathVariable String uuid) {
        return shopQrCodeService.findByUuid(uuid);
    }

    //生成二维码
    @PostMapping("/{shopUuid}")
    public ShopQrCode save(@PathVariable String shopUuid, HttpServletRequest request) throws IOException {
        return shopQrCodeService.save(shopUuid, request);
    }
}
