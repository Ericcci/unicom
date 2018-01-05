package com.jm.unicom.api.shop.service.impl;

import com.jm.unicom.api.shop.dao.PersonalQrCodeDao;
import com.jm.unicom.api.shop.entity.PersonalQrCode;
import com.jm.unicom.api.shop.entity.Shop;
import com.jm.unicom.api.shop.service.PersonalQrCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.api.shop.service.impl
 *          <br><b>Date:</b> 2018/1/4 12:35
 */
@Service
public class PersonalQrCodeServiceImpl implements PersonalQrCodeService {
    @Resource
    private PersonalQrCodeDao personalQrCodeDao;

    @Override
    public List<PersonalQrCode> saveOrUpdate(String shopUuid, List<PersonalQrCode> personalQrCodeList) {
        for (PersonalQrCode personalQrCode : personalQrCodeList) {
            personalQrCode.setShop(new Shop(shopUuid));
            personalQrCodeDao.save(personalQrCode);
        }
        return personalQrCodeList;
    }

    @Override
    public void delete(List<PersonalQrCode> personalQrCodeList) {
        personalQrCodeDao.delete(personalQrCodeList);
    }

    @Override
    public List<PersonalQrCode> get(String shopUuid) {
        return personalQrCodeDao.findByShopUuid(shopUuid);
    }
}
