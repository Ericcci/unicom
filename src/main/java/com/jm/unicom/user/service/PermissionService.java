package com.jm.unicom.user.service;

import com.jm.unicom.user.entity.Permission;

import java.util.List;

/**
 * PermissionService
 *
 * @author Eric
 * @date 2017/12/27
 */
public interface PermissionService {
    List<Permission> findAll();
}
