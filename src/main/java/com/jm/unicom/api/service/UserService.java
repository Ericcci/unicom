package com.jm.unicom.api.service;

import com.jm.unicom.api.entity.User;

import java.util.List;
import java.util.Set;

/**
 * UserService
 *
 * @author Eric
 * @date 2017/12/22
 */
public interface UserService {
    void batchSave(List<User> userList);

    User save(User user);

    User findByUserName(String userName);

    User update(User user);

    User findByUuid(String uuid);
}
