package com.jm.unicom.api.shop.service;

import com.jm.unicom.api.shop.entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * ShopService
 *
 * @author Eric
 * @date 2018/1/2
 */
public interface ShopService {
    /**
     * 保存店铺
     *
     * @param shop 店铺实体类
     * @param request 请求
     * @throws IOException IO异常
     * @return Shop
     */
    Shop save(Shop shop, HttpServletRequest request) throws IOException;

    /**
     * 查找单个店铺
     *
     * @param uuid 店铺uuid
     * @return Shop
     */
    Shop findByUuid(String uuid);

    /**
     * 更新单个店铺
     *
     * @param shop 店铺实体类
     * @return Shop
     */
    Shop update(Shop shop);

    /**
     * 更新单个店铺
     *
     * @param shopList 店铺实体类
     * @return Shop
     */
    void delete(List<Shop> shopList);

    /**
     * 分页查找全部店铺(无条件)
     *
     * @param pageable 分页条件
     * @return Page
     */
    Page<Shop> findAll(Pageable pageable);
}
