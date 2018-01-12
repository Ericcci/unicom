package com.jm.unicom.api.controller;

import com.jm.unicom.api.entity.User;
import com.jm.unicom.core.common.InfoData;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * LoginController
 *
 * @author Eric
 * @date 2017/12/26
 */
@Slf4j
@RestController
@ApiIgnore
public class LoginController {

    @PostMapping("/login")
    public InfoData login(@RequestBody User user, HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
//new SimpleHash("MD5",user.getPassword(),user.getUserName()
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), String.valueOf(new SimpleHash("MD5", user.getPassword(), user.getUserName())));
        try {
            currentUser.login(token);
            return InfoData.success("登陆成功");
        } catch (UnknownAccountException e) {
            return InfoData.fail("登录失败:未知的用户");
        } catch (IncorrectCredentialsException ice) {
            return InfoData.fail("登录失败:错误的凭证");
        } catch (LockedAccountException le) {
            return InfoData.fail("登录失败:账号已被锁定");
        } catch (ExcessiveAttemptsException eae) {
            return InfoData.fail("登录失败:错误次数过多");
        } catch (AuthenticationException ae) {
            return InfoData.fail("登录失败:用户或密码错误");
        }
    }
}
