package com.jm.unicom.shop.service;

import com.jm.unicom.shop.entity.ShopQrCode;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * ShopQrCodeService
 *
 * @author Eric
 * @date 2018/1/2
 */
public interface ShopQrCodeService {
    ShopQrCode findByUuid(String uuid);

    ShopQrCode save(String shopUuid, HttpServletRequest request) throws IOException;
}
