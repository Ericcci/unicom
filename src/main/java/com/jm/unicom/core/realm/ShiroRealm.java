package com.jm.unicom.core.realm;

import com.jm.unicom.api.entity.Permission;
import com.jm.unicom.api.entity.Role;
import com.jm.unicom.api.entity.User;
import com.jm.unicom.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * ShiroRealm
 *
 * @author Eric
 * @date 2017/12/22
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        for (Role role : user.getRoleSet()) {
            authorizationInfo.addRole(role.getRoleName());
            for (Permission permission : role.getPermissionSet()) {
                authorizationInfo.addStringPermission(permission.getPercode());
            }
        }
        log.info("用户" + user.getUserName() + "具有的角色:" + authorizationInfo.getRoles());
        log.info("用户" + user.getUserName() + "具有的权限：" + authorizationInfo.getStringPermissions());
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //将token转换成UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //从转换后的token中获取用户名
        String userName = upToken.getUsername();
        User user = userService.findByUserName(userName);
        if (user != null) {
            // 当验证都通过后，把用户信息放在session里
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("userSession", user);
            session.setAttribute("userName", user.getUserName());
            return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
        } else {
            return null;
        }
    }
}
