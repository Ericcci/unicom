package com.jm.unicom.shop.service;

import com.jm.unicom.shop.entity.QrCode;

import java.io.IOException;

/**
 * QrCodeService
 *
 * @author Eric
 * @date 2018/1/2
 */
public interface QrCodeService {
    QrCode findByUuid(String uuid);
    QrCode save(String shopUuid) throws IOException;
}
