package com.jm.unicom.api.service;

import com.jm.unicom.api.entity.ShopKeeperInfo;

public interface ShopKeeperInfoService {

    /**
     * 保存店铺登录信息
     *
     * @param shopKeeperInfo 店铺登录实体类
     * @return ShopKeeperInfo
     */
    ShopKeeperInfo save(ShopKeeperInfo shopKeeperInfo);
}
