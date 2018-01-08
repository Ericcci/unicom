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
     * 保存顾客信息
     *
     * @param customer 顾客实体类
     * @param shopUuid 店铺uuid
     * @param request  请求
     * @return Customer
     * @throws Exception 异常
     */
    Customer save(Customer customer, String shopUuid, HttpServletRequest request) throws Exception;

    /**
     * 查看该顾客是否有资格抽奖
     *
     * @param shopUuid  店铺uuid
     * @param prizeName 奖品名称
     * @param request   请求
     * @return Customer
     */
    Boolean isQualifications(String shopUuid, String prizeName, HttpServletRequest request);

    /**
     * 获取到奖品又点击抽奖
     *
     * @param shopUuid 店铺uuid
     * @param request  请求
     * @return String
     */
    String getPrizeName(String shopUuid, HttpServletRequest request);
}
