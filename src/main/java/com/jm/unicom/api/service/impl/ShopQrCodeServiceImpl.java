package com.jm.unicom.api.service.impl;

import com.jm.unicom.api.dao.ShopQrCodeDao;
import com.jm.unicom.api.entity.Shop;
import com.jm.unicom.api.entity.ShopQrCode;
import com.jm.unicom.api.service.ShopQrCodeService;
import com.jm.unicom.core.common.ConstantClassField;
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
    public void batchSave(List<String> shopUuidList) throws IOException {
        List<ShopQrCode> shopQrCodeList = new ArrayList<>();
        for (String aShopUuidList : shopUuidList) {
            ShopQrCode shopQrCode = new ShopQrCode();
            shopQrCode.setImgUrl(ConstantClassField.HOST + aShopUuidList);
            shopQrCode.setShop(new Shop(aShopUuidList));
            shopQrCodeList.add(shopQrCode);
        }
        shopQrCodeDao.save(shopQrCodeList);
    }

    @Override
    public ShopQrCode save(String shopUuid) throws IOException {
        ShopQrCode shopQrCode = new ShopQrCode();
        shopQrCode.setImgUrl(ConstantClassField.HOST + shopUuid);
        shopQrCode.setShop(new Shop(shopUuid));
        return shopQrCodeDao.save(shopQrCode);
    }


}
