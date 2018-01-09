package com.jm.unicom.api.controller;

import com.jm.unicom.api.entity.PersonalQrCode;
import com.jm.unicom.api.service.PersonalQrCodeService;
import com.jm.unicom.core.common.InfoData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 * <b>ProjectName:</b> unicom
 * <br><b>PackageName:</b> com.jm.unicom.api.shop.controller
 * <br><b>Date:</b> 2018/1/4 11:34
 */
@Slf4j
@RestController
@RequestMapping("/personalQrCode")
@Api(value = "/personalQrCode", description = "店铺个人收款码操作接口")
public class PersonalQrCodeController {
    @Resource
    private PersonalQrCodeService personalQrCodeService;

    @GetMapping("/get/{shopUuid}")
    @ApiOperation(value = "获取店铺个人收款码", notes = "获取店铺个人收款码", httpMethod = "GET")
    public List<PersonalQrCode> get(@ApiParam(name = "shopUuid", value = "店铺uuid", required = true) @PathVariable String shopUuid) {
        return personalQrCodeService.get(shopUuid);
    }

    @PostMapping("/add/{shopUuid}")
    @ApiOperation(value = "新增店铺个人收款码", notes = "新增店铺个人收款码", httpMethod = "POST")
    public InfoData save(@ApiParam(name = "shopUuid", value = "店铺uuid", required = true) @PathVariable String shopUuid,
                         @ApiParam(name = "personalQrCodeList", value = "个人收款码List集合", required = true) @RequestBody List<PersonalQrCode> personalQrCodeList) {
        return InfoData.success(personalQrCodeService.save(shopUuid, personalQrCodeList), "保存成功");
    }

    @PutMapping("/update/{shopUuid}")
    @ApiOperation(value = "更新店铺个人收款码", notes = "更新店铺个人收款码", httpMethod = "PUT")
    public InfoData update(@ApiParam(name = "shopUuid", value = "店铺uuid", required = true) @PathVariable String shopUuid,
                           @ApiParam(name = "personalQrCodeList", value = "个人收款码List集合", required = true) @RequestBody List<PersonalQrCode> personalQrCodeList) {
        return InfoData.success(personalQrCodeService.update(shopUuid, personalQrCodeList), "更新成功");
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除店铺个人收款码", notes = "删除店铺个人收款码", httpMethod = "DELETE")
    public void delete(@ApiParam(name = "personalQrCodeList", value = "个人收款码List集合", required = true) @RequestBody List<PersonalQrCode> personalQrCodeList) {
        personalQrCodeService.delete(personalQrCodeList);
    }
}
