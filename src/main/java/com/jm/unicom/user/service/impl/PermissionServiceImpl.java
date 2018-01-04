package com.jm.unicom.user.service.impl;

import com.jm.unicom.user.dao.PermissionDao;
import com.jm.unicom.user.entity.Permission;
import com.jm.unicom.user.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * PermissionInitServiceImpl
 *
 * @author Eric
 * @date 2017/12/27
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }
}
