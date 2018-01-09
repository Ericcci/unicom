package com.jm.unicom.api.dao;

import com.jm.unicom.api.entity.ShopQrCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ShopQrCodeDao
 *
 * @author Eric
 * @date 2018/1/2
 */
public interface ShopQrCodeDao extends JpaRepository<ShopQrCode,String>{
}
