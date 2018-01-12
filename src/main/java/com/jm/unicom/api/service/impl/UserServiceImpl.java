package com.jm.unicom.api.service.impl;

import com.jm.unicom.api.dao.UserDao;
import com.jm.unicom.api.entity.User;
import com.jm.unicom.api.service.UserService;
import com.jm.unicom.core.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * UserServiceImpl
 *
 * @author Eric
 * @date 2017/12/22
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void batchSave(List<User> userList) {
        userDao.save(userList);
    }

    @Override
    public User save(User user) {
        if (userDao.findByUserName(user.getUserName()) == null) {
            user.setPassword(String.valueOf(new SimpleHash("MD5", user.getPassword(), user.getUserName())));
            return userDao.save(user);
        }
        return null;
    }


    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public User update(User user) {
        return userDao.save(user);
    }

    @Override
    public User findByUuid(String uuid) {
        return userDao.findOne(uuid);
    }
}
