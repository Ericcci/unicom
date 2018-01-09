package com.jm.unicom.api.dao;

import com.jm.unicom.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.api.customer.dao
 *          <br><b>Date:</b> 2018/1/5 17:19
 */
public interface CustomerDao extends JpaRepository<Customer,String>{
}
