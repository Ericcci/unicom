package com.jm.unicom.api.shop.service;

import com.jm.unicom.api.shop.entity.PersonalQrCode;

import java.util.List;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.api.shop.service
 *          <br><b>Date:</b> 2018/1/4 11:45
 */
public interface PersonalQrCodeService {
    /**
     * 保存或更新个人收款码
     *
     * @param shopUuid           店铺uuid
     * @param personalQrCodeList 收款码详情
     * @return List<PersonalQrCode>
     */
    List<PersonalQrCode> saveOrUpdate(String shopUuid, List<PersonalQrCode> personalQrCodeList);

    /**
     * 删除个人收款码
     *
     * @param personalQrCodeList 收款码详情
     */
    void delete(List<PersonalQrCode> personalQrCodeList) throws Exception;

    /**
     * 获取个人收款码
     *
     * @param shopUuid 店铺uuid
     * @return List<PersonalQrCode>
     */
    List<PersonalQrCode> get(String shopUuid);
}
