package com.jm.unicom.shop.service;

import com.jm.unicom.shop.entity.ShopQrCode;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * ShopQrCodeService
 *
 * @author Eric
 * @date 2018/1/2
 */
public interface ShopQrCodeService {

    ShopQrCode findByUuid(String shopUuid);

    ShopQrCode save(String shopUuid, HttpServletRequest request) throws IOException;
}
