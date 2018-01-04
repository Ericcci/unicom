package com.jm.unicom.shop.service.impl;

import com.jm.unicom.common.ConstantClassField;
import com.jm.unicom.shop.dao.ShopQrCodeDao;
import com.jm.unicom.shop.entity.Shop;
import com.jm.unicom.shop.entity.ShopQrCode;
import com.jm.unicom.shop.service.ShopQrCodeService;
import com.jm.unicom.util.QrCodeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * ShopQrCodeServiceImpl
 *
 * @author Eric
 * @date 2018/1/2
 */
@Service
public class ShopQrCodeServiceImpl implements ShopQrCodeService {
    @Resource
    private ShopQrCodeDao shopQrCodeDao;

    @Override
    public ShopQrCode findByUuid(String shopUuid) {
        return shopQrCodeDao.findOne(shopUuid);
    }

    @Override
    public ShopQrCode save(String shopUuid, HttpServletRequest request) throws IOException {
        ShopQrCode shopQrCode = new ShopQrCode();
        shopQrCode.setImgUrl(ConstantClassField.HOST + shopUuid);
        shopQrCode.setImgData(ConstantClassField.BASE64_HEAD + QrCodeUtil.getQrCode(ConstantClassField.QRCODE_URL + ConstantClassField.HOST + shopUuid));
        shopQrCode.setShop(new Shop(shopUuid));
        return shopQrCodeDao.save(shopQrCode);
    }
}
