package com.jm.unicom.api.shop.service.impl;

import com.jm.unicom.api.shop.dao.ShopDao;
import com.jm.unicom.api.shop.entity.Shop;
import com.jm.unicom.api.shop.service.ShopQrCodeService;
import com.jm.unicom.api.shop.service.ShopService;
import com.jm.unicom.common.ConstantClassField;
import com.jm.unicom.core.service.RedisService;
import com.jm.unicom.core.util.ExcelUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private RedisService redisService;

    @Resource
    private ShopQrCodeService shopQrCodeService;

    @Override
    public Shop save(Shop shop) throws IOException {
        shopDao.save(shop);
        shopQrCodeService.save(shop.getUuid());
        return shop;
    }

    @Override
    public boolean importExcel(MultipartFile[] files) throws Exception {
        List<Shop> shopList = ExcelUtil.importExcel(files);
        if (shopList.size() > 0) {
            shopDao.save(shopList);
            for (Shop shop : shopList) {
                shopQrCodeService.save(shop.getUuid());
            }
            return true;
        }
        return false;
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

    @Override
    public boolean exportExcel(List<Shop> shopList, HttpServletRequest request, HttpServletResponse response) {
        try {
            ExcelUtil.exportExcel(shopList, request, response);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
