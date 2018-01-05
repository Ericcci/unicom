package com.jm.unicom.api.shop.dao;

import com.jm.unicom.api.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ShopDao
 *
 * @author Eric
 * @date 2018/1/2
 */
public interface ShopDao extends JpaRepository<Shop, String>, JpaSpecificationExecutor<Shop> {
}
