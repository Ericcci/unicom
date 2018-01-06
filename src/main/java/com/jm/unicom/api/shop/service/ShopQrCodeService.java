package com.jm.unicom.api.shop.service;

import com.jm.unicom.api.shop.entity.ShopQrCode;

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
