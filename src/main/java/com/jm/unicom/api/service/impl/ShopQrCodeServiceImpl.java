package com.jm.unicom.api.service.impl;

import com.jm.unicom.api.dao.ShopQrCodeDao;
import com.jm.unicom.api.entity.Shop;
import com.jm.unicom.api.entity.ShopQrCode;
import com.jm.unicom.api.service.ShopQrCodeService;
import com.jm.unicom.core.common.ConstantClassField;
import com.jm.unicom.core.util.QrCodeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public ShopQrCode save(List<String> shopUuidList) throws IOException {
        List<ShopQrCode> shopQrCodeList = new ArrayList<>();
        for(int i =0;i<shopUuidList.size();i++){
            ShopQrCode shopQrCode = new ShopQrCode();
            shopQrCode.setImgUrl(ConstantClassField.HOST + shopUuidList.get(i));
            shopQrCode.setImgData(ConstantClassField.BASE64_HEAD + QrCodeUtil.getQrCode(ConstantClassField.QRCODE_URL + ConstantClassField.HOST + shopUuidList.get(i)));
            shopQrCode.setShop(new Shop(shopUuidList.get(i)));
            shopQrCodeList.add(shopQrCode);
        }

        return shopQrCodeDao.save(shopQrCode);
    }
}
