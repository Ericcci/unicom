package com.jm.unicom.api.service.impl;

import com.jm.unicom.api.dao.ShopDao;
import com.jm.unicom.api.entity.Shop;
import com.jm.unicom.api.entity.User;
import com.jm.unicom.api.service.ShopQrCodeService;
import com.jm.unicom.api.service.ShopService;
import com.jm.unicom.api.service.UserService;
import com.jm.unicom.core.common.ConstantClassField;
import com.jm.unicom.core.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ShopServiceImpl
 *
 * @author Eric
 * @date 2018/1/2
 */
@Slf4j
@Service
public class ShopServiceImpl implements ShopService {

    @Resource
    private ShopDao shopDao;

    @Resource
    private ShopQrCodeService shopQrCodeService;

    @Resource
    private UserService userService;

    @Override
    @Transactional
    public Shop save(Shop shop) throws IOException {
        User user = userService.findByUserName(shop.getTelephone());
        if (user != null) {
            shop.setUser(new User(user.getUuid()));
            shopDao.save(shop);
            shopQrCodeService.save(shop.getUuid());
        } else {
            User temp = new User();
            temp.setPassword(ConstantClassField.DEFAULT_PASSWORD);
            temp.setUserName(shop.getTelephone());
            userService.save(temp);
            shop.setUser(new User(temp.getUuid()));
            shopDao.save(shop);
            shopQrCodeService.save(shop.getUuid());
        }
        return shop;
    }


    @Override
    @Transactional
    public boolean importExcel(MultipartFile[] files) throws Exception {
        List<Shop> shopList = ExcelUtil.importExcel(files);
        Set<User> userSet = new HashSet<>();
        List<String> shopUuidList = new ArrayList<>();
        if (shopList.size() > 0) {
            for (Shop aShopList : shopList) {
                User user = userService.findByUserName(aShopList.getTelephone());
                if (user != null) {
                    aShopList.setUser(new User(user.getUuid()));
                } else {
                    User temp = new User();
                    temp.setUserName(aShopList.getTelephone());
                    temp.setPassword(ConstantClassField.DEFAULT_PASSWORD);
                    userSet.add(temp);
                }
            }
            if (userSet.size() > 0) {
                List<User> userList = new ArrayList<>(userSet);
                userService.batchSave(userList);
                for (User anUserList : userList) {
                    for (Shop aShopList : shopList) {
                        if (aShopList.getTelephone().equals(anUserList.getUserName())) {
                            aShopList.setUser(new User(anUserList.getUuid()));
                        }
                    }
                }
            }
            shopDao.save(shopList);
            for (Shop aShopList : shopList) {
                shopUuidList.add(aShopList.getUuid());
            }
            shopQrCodeService.batchSave(shopUuidList);
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
        String user = (String) SecurityUtils.getSubject().getSession().getAttribute("userName");
        Page<Shop> shops = shopDao.findAll(new Specification<Shop>() {
            @Override
            public Predicate toPredicate(Root<Shop> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (("admin").equals(user)) {
                    criteriaQuery.where(criteriaBuilder.equal(root.get("status").as(Integer.class), 1));
                } else {
                    criteriaQuery.where(criteriaBuilder.equal(root.get("status").as(Integer.class), 1),
                            criteriaBuilder.equal(root.get("telephone").as(String.class), user));
                }
                return null;
            }

        }, pageable);
        return shops;
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
