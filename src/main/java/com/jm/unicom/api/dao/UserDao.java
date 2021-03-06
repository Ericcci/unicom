package com.jm.unicom.api.dao;

import com.jm.unicom.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserDao
 *
 * @author Eric
 * @date 2017/12/21
 */

public interface UserDao extends JpaRepository<User, String> {

    User findByUserName(String userName);
}
