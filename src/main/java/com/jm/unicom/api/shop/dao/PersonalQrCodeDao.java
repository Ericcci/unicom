package com.jm.unicom.api.shop.dao;

import com.jm.unicom.api.shop.entity.PersonalQrCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.api.shop.dao
 *          <br><b>Date:</b> 2018/1/4 12:38
 */
public interface PersonalQrCodeDao extends JpaRepository<PersonalQrCode, String> {
    List<PersonalQrCode> findByShopUuid(String shopUuid);
}
