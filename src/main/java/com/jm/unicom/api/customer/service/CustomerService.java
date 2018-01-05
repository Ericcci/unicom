package com.jm.unicom.api.customer.service;

import com.jm.unicom.api.customer.entity.Customer;

import javax.servlet.http.HttpServletRequest;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.api.customer.service
 *          <br><b>Date:</b> 2018/1/5 17:18
 */
public interface CustomerService {
    /**
     * 保存店铺
     *
     * @param customer 顾客实体类
     * @param shopUuid 店铺uuid
     * @param request  请求
     * @return Customer
     */
    Customer save(Customer customer, String shopUuid, HttpServletRequest request) throws Exception;
}
