package com.jm.unicom.shop.service;

import com.jm.unicom.shop.entity.Shop;

import java.io.IOException;

/**
 * ShopService
 *
 * @author Eric
 * @date 2018/1/2
 */
public interface ShopService {
    void save(Shop shop) throws IOException;
    Shop findByUuid(String uuid);
    void update(Shop shop);
}
