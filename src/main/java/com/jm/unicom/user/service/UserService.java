package com.jm.unicom.user.service;

import com.jm.unicom.user.entity.User;

import java.util.Set;

/**
 * UserService
 *
 * @author Eric
 * @date 2017/12/22
 */
public interface UserService {
    User findByUuid(String uuid);
    User findByUserName(String userName);
    void save(User user);
    void update(User user);
    Set<String> getRoles(String userName);
    Set<String> getPermissions(String userName);
}
