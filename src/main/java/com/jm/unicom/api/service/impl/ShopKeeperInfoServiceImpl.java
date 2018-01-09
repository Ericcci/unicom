package com.jm.unicom.api.service.impl;

import com.jm.unicom.api.dao.ShopKeeperInfoDao;
import com.jm.unicom.api.entity.ShopKeeperInfo;
import com.jm.unicom.api.service.ShopKeeperInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ShopKeeperInfoServiceImpl
 *
 * @author Eric
 * @date 2018/1/9
 */
@Service
public class ShopKeeperInfoServiceImpl implements ShopKeeperInfoService {
    @Resource
    private ShopKeeperInfoDao shopKeeperInfoDao;

    @Override
    public ShopKeeperInfo save(ShopKeeperInfo shopKeeperInfo) {
        return shopKeeperInfoDao.save(shopKeeperInfo);
    }
}
