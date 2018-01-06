package com.jm.unicom.api.shop.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.jm.unicom.api.shop.dao.ShopDao;
import com.jm.unicom.api.shop.entity.Shop;
import com.jm.unicom.api.shop.service.ShopQrCodeService;
import com.jm.unicom.api.shop.service.ShopService;
import com.jm.unicom.common.ConstantClassField;
import com.jm.unicom.core.service.RedisService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                ImportParams params = new ImportParams();
                List<Shop> shopList = ExcelImportUtil.importExcel(file.getInputStream(), Shop.class, params);
                shopDao.save(shopList);
                for (Shop shop : shopList) {
                    shopQrCodeService.save(shop.getUuid());
                }
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
        if (!(redisService.exists("shop" + pageable))) {
            redisService.set("shop" + pageable, shopDao.findAll(specification, pageable), ConstantClassField.SHOP_EXPIRE_TIME);
        }
        return (Page<Shop>) redisService.get("shop" + pageable);
    }
}
