package com.jm.unicom.api.service;

import com.jm.unicom.api.entity.ShopQrCode;

import java.io.IOException;

/**
 * ShopQrCodeService
 *
 * @author Eric
 * @date 2018/1/2
 */
public interface ShopQrCodeService {

    ShopQrCode findByUuid(String shopUuid);

    ShopQrCode save(String shopUuid) throws IOException;
}
