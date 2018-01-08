package com.jm.unicom.api.shop.service;

import com.jm.unicom.api.shop.entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * @return Shop
     * @throws IOException IO异常
     */
    Shop save(Shop shop) throws IOException;

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
     * 批量删除/单个删除店铺
     *
     * @param shopList 店铺实体类
     */
    void delete(List<Shop> shopList);

    /**
     * 分页查找全部店铺(无条件)
     *
     * @param pageable 分页条件
     * @return Page
     */
    Page<Shop> findAll(Pageable pageable);

    /**
     * Excel导入店铺
     *
     * @param files Excel文件
     * @return boolean
     * @throws Exception 异常
     */
    boolean importExcel(MultipartFile[] files) throws Exception;

    /**
     * Excel导出店铺
     *
     * @param shopList 店铺
     * @param request  请求
     * @param response 回应
     * @return boolean
     * @throws IOException 异常
     */
    boolean exportExcel(List<Shop> shopList, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
