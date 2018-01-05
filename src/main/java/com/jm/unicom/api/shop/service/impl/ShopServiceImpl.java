package com.jm.unicom.api.shop.service.impl;

import com.jm.unicom.api.shop.dao.ShopDao;
import com.jm.unicom.api.shop.entity.Shop;
import com.jm.unicom.api.shop.service.ShopQrCodeService;
import com.jm.unicom.api.shop.service.ShopService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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

    @Override
    public void delete(List<Shop> shopList) {
            shopDao.save(shopList);
    }

    @Override
    public Page<Shop> findAll(Pageable pageable) {
        Specification<Shop> specification = (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("status").as(Integer.class), 1);
        return shopDao.findAll(specification, pageable);
    }
}
