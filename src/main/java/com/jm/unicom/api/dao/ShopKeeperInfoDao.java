package com.jm.unicom.api.dao;

import com.jm.unicom.api.entity.ShopKeeperInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ShopKeeperInfoDao
 *
 * @author Eric
 * @date 2018/1/9
 */
public interface ShopKeeperInfoDao extends JpaRepository<ShopKeeperInfo, String> {
}
