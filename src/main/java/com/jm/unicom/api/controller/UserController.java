package com.jm.unicom.api.controller;

import com.jm.unicom.core.common.InfoData;
import com.jm.unicom.api.entity.User;
import com.jm.unicom.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
@ApiIgnore
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/get/{uuid}")
    public User get(@PathVariable String uuid) {
        return userService.findByUuid(uuid);
    }

    @GetMapping("/get/findByUserName/{userName}")
    public InfoData findByUserName(@PathVariable String userName) {
        if (userService.findByUserName(userName) != null) {
            return InfoData.success(userService.findByUserName(userName), "获取成功");
        }
        return InfoData.fail("获取失败");
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
