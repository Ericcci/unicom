package com.jm.unicom.api.service;

import com.jm.unicom.api.entity.ShopQrCode;

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

    void batchSave(List<String> shopUuidList) throws IOException;

    ShopQrCode save(String shopUuid) throws IOException;
}
