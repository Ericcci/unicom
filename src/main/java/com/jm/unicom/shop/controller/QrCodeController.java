package com.jm.unicom.shop.controller;

import com.jm.unicom.shop.entity.QrCode;
import com.jm.unicom.shop.service.QrCodeService;
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
@RequestMapping("/qrcode")
public class QrCodeController {
    @Resource
    private QrCodeService qrCodeService;

    @GetMapping("/{uuid}")
    public QrCode get(@PathVariable String uuid){
        return qrCodeService.findByUuid(uuid);
    }

    //生成二维码
    @PostMapping("/{shopUuid}")
    public QrCode save(@PathVariable String shopUuid) throws IOException {
        return qrCodeService.save(shopUuid);
    }
}
