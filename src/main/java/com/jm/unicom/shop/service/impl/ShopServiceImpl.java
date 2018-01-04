package com.jm.unicom.shop.service.impl;

import com.jm.unicom.shop.dao.ShopDao;
import com.jm.unicom.shop.entity.Shop;
import com.jm.unicom.shop.service.ShopQrCodeService;
import com.jm.unicom.shop.service.ShopService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    private ShopQrCodeService shopQrCodeService;

    @Override
    public Shop save(Shop shop, HttpServletRequest request) throws IOException {
        shopDao.save(shop);
        shopQrCodeService.save(shop.getUuid(), request);
        return shop;
    }

    @Override
    public Shop findByUuid(String uuid) {
        return shopDao.findOne(uuid);
    }

    @Override
    public Shop update(Shop shop) {
        return shopDao.save(shop);
    }
}
