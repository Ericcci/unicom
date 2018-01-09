package com.jm.unicom.api.controller;

import com.jm.unicom.api.entity.ShopQrCode;
import com.jm.unicom.api.service.ShopQrCodeService;
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
public class ShopQrCodeController {
    @Resource
    private ShopQrCodeService shopQrCodeService;

    @GetMapping("/{uuid}")
    public ShopQrCode get(@PathVariable String uuid) {
        return shopQrCodeService.findByUuid(uuid);
    }

    @PostMapping("/{shopUuid}")
    public ShopQrCode save(@PathVariable String shopUuid) throws IOException {
        return shopQrCodeService.save(shopUuid);
    }
}