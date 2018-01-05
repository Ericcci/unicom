package com.jm.unicom.api.shop.controller;

import com.jm.unicom.common.InfoData;
import com.jm.unicom.api.shop.entity.Shop;
import com.jm.unicom.api.shop.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * ShopController
 *
 * @author Eric
 * @date 2018/1/2
 */
@Slf4j
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private ShopService shopService;

    @GetMapping("/{uuid}")
    public InfoData getOneShop(@PathVariable String uuid) {
        Shop shop = shopService.findByUuid(uuid);
        if (shop!=null){
            return InfoData.success(shopService.findByUuid(uuid), "获取成功");
        }
        return InfoData.fail("获取失败");
    }

    @PostMapping
    public InfoData save(@RequestBody Shop shop, HttpServletRequest request) throws IOException {
        return InfoData.success(shopService.save(shop, request), "保存成功");
    }

    @PutMapping
    public InfoData update(@RequestBody Shop shop) {
        return InfoData.success(shopService.update(shop), "更新成功");
    }

    @PutMapping("/delete")
    public InfoData delete(@RequestBody List<Shop> shopList) {
        shopService.delete(shopList);
        return InfoData.success("删除成功");
    }

    @GetMapping
    public Page<Shop> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "15") Integer size,
                                 @RequestParam(value = "sorts", defaultValue = "createTime") String sorts) {
        Sort sort = new Sort(Sort.Direction.DESC, sorts);
        Pageable pageable = new PageRequest(page, size, sort);
        return shopService.findAll(pageable);
    }
}
