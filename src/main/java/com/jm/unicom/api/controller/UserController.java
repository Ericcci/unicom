package com.jm.unicom.api.controller;

import com.jm.unicom.core.common.InfoData;
import com.jm.unicom.api.entity.User;
import com.jm.unicom.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * UserController
 *
 * @author Eric
 * @date 2017/12/21
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/{uuid}")
    public User get(@PathVariable String uuid) {
        return userService.findByUuid(uuid);
    }

    @PostMapping
    public InfoData save(@RequestBody User user) {
        userService.save(user);
        return InfoData.success(user, "保存成功");
    }

    @PutMapping
    public InfoData update(@RequestBody User updateUser) {
        userService.update(updateUser);
        return InfoData.success(updateUser, "更新成功");
    }

    @GetMapping("/add")
    public String home() {
        return "你好，Spring Boot";
    }
}
