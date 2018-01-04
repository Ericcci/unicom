package com.jm.unicom.shop.service.impl;

import com.jm.unicom.common.ConstantClassField;
import com.jm.unicom.shop.dao.QrCodeDao;
import com.jm.unicom.shop.entity.QrCode;
import com.jm.unicom.shop.entity.Shop;
import com.jm.unicom.shop.service.QrCodeService;
import com.jm.unicom.util.QrCodeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * QrCodeServiceImpl
 *
 * @author Eric
 * @date 2018/1/2
 */
@Service
public class QrCodeServiceImpl implements QrCodeService {
    @Resource
    private QrCodeDao qrCodeDao;

    @Override
    public QrCode findByUuid(String shopUuid) {
        return qrCodeDao.findOne(shopUuid);
    }

    @Override
    public QrCode save(String shopUuid) throws IOException {
        QrCode qrCode = new QrCode();
        qrCode.setImgUrl(ConstantClassField.HOST + shopUuid);
        qrCode.setImgData(ConstantClassField.BASE64_HEAD + QrCodeUtil.getQrCode(ConstantClassField.QRCODE_URL + ConstantClassField.HOST + shopUuid));
        qrCode.setShop(new Shop(shopUuid));
        return qrCodeDao.save(qrCode);
    }
}
