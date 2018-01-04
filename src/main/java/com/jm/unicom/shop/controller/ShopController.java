package com.jm.unicom.shop.controller;

import com.jm.unicom.annotation.RequestLimit;
import com.jm.unicom.common.InfoData;
import com.jm.unicom.shop.entity.Shop;
import com.jm.unicom.shop.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.Line;
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
    public Shop shop(@ApiParam(name = "uuid", value = "店铺uuid", required = true) @PathVariable String uuid) {
        return shopService.findByUuid(uuid);
    }

    @PostMapping
    public InfoData save(@RequestBody Shop shop, HttpServletRequest request) throws IOException {
        return InfoData.success(shopService.save(shop, request), "保存成功");
    }

    @PutMapping
    public InfoData update(@RequestBody Shop shop) {
        return InfoData.success(shopService.update(shop), "更新成功");
    }

    //@RequestLimit(count = 1, time = 6000)
    @GetMapping("/h")
    public String hh() {
        return "ggg";
    }
}
