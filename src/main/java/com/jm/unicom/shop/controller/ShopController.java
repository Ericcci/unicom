package com.jm.unicom.shop.controller;

import com.jm.unicom.common.InfoData;
import com.jm.unicom.shop.entity.Shop;
import com.jm.unicom.shop.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * ShopController
 *
 * @author Eric
 * @date 2018/1/2
 */
@Slf4j
@RestController
@RequestMapping("/shop")
@Api(value = "店铺操作接口")
public class ShopController {

    @Resource
    private ShopService shopService;

    @ApiOperation(value = "获取店铺信息")
    @GetMapping("/{uuid}")
    public InfoData getOneShop(@ApiParam(name = "uuid", value = "店铺uuid", required = true) @PathVariable String uuid) {
        return InfoData.success(shopService.findByUuid(uuid), "获取成功");
    }

    @PostMapping
    public InfoData save(@RequestBody Shop shop, HttpServletRequest request) throws IOException {
        return InfoData.success(shopService.save(shop, request), "保存成功");
    }

    @PutMapping
    public InfoData update(@RequestBody Shop shop) {
        return InfoData.success(shopService.update(shop), "更新成功");
    }

    @GetMapping
    public Page<Shop> getAllShop(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "15") Integer size,
                                 @RequestParam(value = "sorts", defaultValue = "createTime") String sorts) {
        Sort sort = new Sort(Sort.Direction.DESC, sorts);
        Pageable pageable = new PageRequest(page, size, sort);
        return shopService.findAll(pageable);
    }
}
