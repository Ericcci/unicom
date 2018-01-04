package com.jm.unicom.shop.service;

import com.jm.unicom.shop.entity.Shop;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * ShopService
 *
 * @author Eric
 * @date 2018/1/2
 */
public interface ShopService {
    Shop save(Shop shop, HttpServletRequest request) throws IOException;

    Shop findByUuid(String uuid);

    Shop update(Shop shop);
}
