package com.jm.unicom.shop.dao;

import com.jm.unicom.shop.entity.PersonalQrCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.shop.dao
 *          <br><b>Date:</b> 2018/1/4 12:38
 */
public interface PersonalQrCodeDao extends JpaRepository<PersonalQrCode,String> {
}
