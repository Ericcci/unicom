package com.jm.unicom.shop.dao;

import com.jm.unicom.shop.entity.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * QrCodeDao
 *
 * @author Eric
 * @date 2018/1/2
 */
public interface QrCodeDao extends JpaRepository<QrCode,String>{
}
