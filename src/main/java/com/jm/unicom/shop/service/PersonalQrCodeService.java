package com.jm.unicom.shop.service;

import com.jm.unicom.shop.entity.PersonalQrCode;

import java.util.List;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.shop.service
 *          <br><b>Date:</b> 2018/1/4 11:45
 */
public interface PersonalQrCodeService {
    /**
     * 保存个人收款码
     *
     * @param shopUuid 店铺uuid
     * @param personalQrCodeList 收款码详情
     * @return List<PersonalQrCode>
     */
    List<PersonalQrCode> save(String shopUuid, List<PersonalQrCode> personalQrCodeList);

    /**
     * 更新个人收款码
     *
     * @param personalQrCodeList 收款码详情
     * @return List<PersonalQrCode>
     */
    List<PersonalQrCode> update(List<PersonalQrCode> personalQrCodeList);
}
