package com.jm.unicom.shop.controller;

import com.jm.unicom.common.InfoData;
import com.jm.unicom.shop.entity.PersonalQrCode;
import com.jm.unicom.shop.service.PersonalQrCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.shop.controller
 *          <br><b>Date:</b> 2018/1/4 11:34
 */
@Slf4j
@RestController
@RequestMapping("/personalQrCode")
public class PersonalQrCodeController {
    @Resource
    private PersonalQrCodeService personalQrCodeService;

    @GetMapping("/{shopUuid}")
    public List<PersonalQrCode> get(@PathVariable String shopUuid) {
        return personalQrCodeService.get(shopUuid);
    }

    @PostMapping("/{shopUuid}")
    public InfoData saveOrUpdate(@PathVariable String shopUuid, @RequestBody List<PersonalQrCode> personalQrCodeList) {
        return InfoData.success(personalQrCodeService.saveOrUpdate(shopUuid, personalQrCodeList), "保存(更新)成功");
    }

    @DeleteMapping
    public InfoData delete(@RequestBody List<PersonalQrCode> personalQrCodeList) {
        personalQrCodeService.delete(personalQrCodeList);
        return InfoData.success("删除成功");
    }
}
