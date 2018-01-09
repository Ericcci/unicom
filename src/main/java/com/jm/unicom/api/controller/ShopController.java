package com.jm.unicom.api.controller;

import com.jm.unicom.api.entity.Shop;
import com.jm.unicom.api.service.ShopService;
import com.jm.unicom.core.common.InfoData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@Api(value = "/shop", description = "店铺操作接口")
public class ShopController {

    @Resource
    private ShopService shopService;

    @GetMapping("/{uuid}")
    @ApiOperation(value = "获取单个店铺信息", notes = "获取单个店铺信息", httpMethod = "GET")
    public InfoData getOneShop(@ApiParam(name = "uuid", value = "店铺uuid", required = true) @PathVariable String uuid) {
        Shop shop = shopService.findByUuid(uuid);
        if (shop != null) {
            return InfoData.success(shopService.findByUuid(uuid), "获取成功");
        }
        return InfoData.fail("获取失败");
    }

    @PostMapping
    @ApiOperation(value = "保存店铺信息", notes = "保存店铺信息", httpMethod = "POST")
    public InfoData save(@ApiParam(name = "shop", value = "店铺实体类", required = true) @RequestBody Shop shop) throws IOException {
        return InfoData.success(shopService.save(shop), "保存成功");
    }

    @PutMapping
    @ApiOperation(value = "更新店铺信息", notes = "更新店铺信息", httpMethod = "PUT")
    public InfoData update(@ApiParam(name = "shop", value = "店铺实体类", required = true) @RequestBody Shop shop) {
        return InfoData.success(shopService.update(shop), "更新成功");
    }

    @PutMapping("/delete")
    @ApiOperation(value = "删除店铺信息", notes = "删除店铺信息", httpMethod = "PUT")
    public InfoData delete(@ApiParam(name = "shopList", value = "店铺实体集合类", required = true) @RequestBody List<Shop> shopList) {
        shopService.delete(shopList);
        return InfoData.success("删除成功");
    }

    @GetMapping
    @ApiOperation(value = "分页获取店铺信息", notes = "分页获取店铺信息", httpMethod = "GET")
    public Page<Shop> findAll(@ApiParam(name = "page", value = "页数") @RequestParam(value = "page", defaultValue = "0") Integer page,
                              @ApiParam(name = "size", value = "数量") @RequestParam(value = "size", defaultValue = "15") Integer size,
                              @ApiParam(name = "sorts", value = "排序") @RequestParam(value = "sorts", defaultValue = "createTime") String sorts) {
        Sort sort = new Sort(Sort.Direction.DESC, sorts);
        Pageable pageable = new PageRequest(page, size, sort);
        return shopService.findAll(pageable);
    }

    @PostMapping("/exportShop")
    @ApiOperation(value = "Excel导出店铺信息", notes = "Excel导出店铺信息", httpMethod = "POST")
    public InfoData exportShop(@ApiParam(name = "shopList", value = "店铺实体集合类", required = true) @RequestBody List<Shop> shopList, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return shopService.exportExcel(shopList, request, response) ? InfoData.success("导出成功") : InfoData.fail("导出失败");
    }

    @PostMapping("/importShop")
    @ApiOperation(value = "Excel导入店铺信息", notes = "Excel导入店铺信息", httpMethod = "POST")
    public InfoData importShop(@ApiParam(name = "files", value = "导入的文件", required = true) @RequestParam(value = "files") MultipartFile[] files) throws Exception {
        return shopService.importExcel(files) ? InfoData.success("导入成功") : InfoData.fail("导入失败");
    }
}

