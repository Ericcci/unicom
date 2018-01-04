package com.jm.unicom.shop.service.impl;

import com.jm.unicom.shop.dao.ShopDao;
import com.jm.unicom.shop.entity.QrCode;
import com.jm.unicom.shop.entity.Shop;
import com.jm.unicom.shop.service.QrCodeService;
import com.jm.unicom.shop.service.ShopService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * ShopServiceImpl
 *
 * @author Eric
 * @date 2018/1/2
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Resource
    private ShopDao shopDao;

    @Resource
    private QrCodeService qrCodeService;

    @Override
    public void save(Shop shop) throws IOException {
        shopDao.save(shop);
        qrCodeService.save(shop.getUuid());
    }

    @Override
    public Shop findByUuid(String uuid) {
        return shopDao.findOne(uuid);
    }

    @Override
    public void update(Shop shop) {
        shopDao.save(shop);
    }
}
