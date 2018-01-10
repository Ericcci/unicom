package com.jm.unicom.api.service.impl;

import com.jm.unicom.api.dao.ShopDao;
import com.jm.unicom.api.entity.Shop;
import com.jm.unicom.api.entity.User;
import com.jm.unicom.api.service.ShopQrCodeService;
import com.jm.unicom.api.service.ShopService;
import com.jm.unicom.api.service.UserService;
import com.jm.unicom.core.common.ConstantClassField;
import com.jm.unicom.core.util.ExcelUtil;
import com.jm.unicom.core.util.MD5Util;
import com.jm.unicom.core.util.UUIDUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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

    @Resource
    private UserService userService;

    @Override
    public Shop save(Shop shop) throws IOException {
        User user = userService.findByUserName(shop.getTelpohone());
        String userUuid = UUIDUtil.getUUID();
        if (user != null) {
            shop.setUser(new User(user.getUuid()));
            shopDao.save(shop);
            shopQrCodeService.save(shop.getUuid());
        } else {
            User temp = new User();
            shop.setUser(new User(userUuid));
            temp.setUuid(userUuid);
            temp.setPassword(MD5Util.getMD5Password(ConstantClassField.DEFAULT_PASSWORD, userUuid));
            temp.setUserName(shop.getTelpohone());
            userService.save(temp);
            shopDao.save(shop);
            shopQrCodeService.save(shop.getUuid());
        }
        return shop;
    }

    @Override
    public boolean importExcel(MultipartFile[] files) throws Exception {
        List<Shop> shopList = ExcelUtil.importExcel(files);
        List<User> userList = new ArrayList<>();
        List<String> shopUuidList = new ArrayList<>();
        User temp = new User();
        if (shopList.size() > 0) {
            //shopDao.save(shopList);
            for (Shop aShopList : shopList) {
                //shopQrCodeService.save(aShopList.getUuid());
                String userUuid = UUIDUtil.getUUID();
                User user = userService.findByUserName(aShopList.getTelpohone());
                if (user != null) {
                    aShopList.setUser(new User(user.getUuid()));
                    //shopDao.save(aShopList);
                    shopUuidList.add(aShopList.getUuid());
                    //shopQrCodeService.save(aShopList.getUuid());
                } else {
                    aShopList.setUser(new User(userUuid));
                    temp.setUuid(userUuid);
                    temp.setUserName(aShopList.getTelpohone());
                    temp.setPassword(MD5Util.getMD5Password(ConstantClassField.DEFAULT_PASSWORD, userUuid));
                    userList.add(temp);
                    shopUuidList.add(aShopList.getUuid());
                    //userService.save(temp);
                    //shopDao.save(aShopList);
                    //shopQrCodeService.save(aShopList.getUuid());
                }
            }
            shopDao.save(shopList);
            shopQrCodeService.save(shopUuidList);
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
