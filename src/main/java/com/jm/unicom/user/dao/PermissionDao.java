package com.jm.unicom.user.dao;

import com.jm.unicom.user.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * PermissionDao
 *
 * @author Eric
 * @date 2017/12/27
 */
public interface PermissionDao extends JpaRepository<Permission, String> {

    @Override
    List<Permission> findAll();
}
