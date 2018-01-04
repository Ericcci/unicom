package com.jm.unicom.shop.dao;

import com.jm.unicom.shop.entity.ShopQrCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ShopQrCodeDao
 *
 * @author Eric
 * @date 2018/1/2
 */
public interface ShopQrCodeDao extends JpaRepository<ShopQrCode,String>{
}
