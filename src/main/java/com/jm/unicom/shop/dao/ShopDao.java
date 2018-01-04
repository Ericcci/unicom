package com.jm.unicom.shop.dao;

import com.jm.unicom.shop.entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
