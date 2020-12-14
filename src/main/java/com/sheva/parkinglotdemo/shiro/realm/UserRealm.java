package com.sheva.parkinglotdemo.shiro.realm;

import com.sheva.parkinglotdemo.entity.User;
import com.sheva.parkinglotdemo.service.UserService;
import com.sheva.parkinglotdemo.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Sheva
 * @Date 2020/12/3
 */
@Slf4j
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String useranme = (String) principals.getPrimaryPrincipal();
        log.info("当前用户为: [" + useranme + "]");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String roleName = userService.findRoles(useranme);
        Set<String> set = new HashSet<>();
        set.add(roleName);
        info.setRoles(set);
        log.info("当前用户角色为： [" + roleName + "]");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken)token;
        String username= upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null){
            password = EncryptUtil.encryptPassword(username, new String(upToken.getPassword()));
            log.info("前台输入密码加密后： " + password);
        }
        User user = userService.findByUsername(username);
        if (null == user || !password.equals(user.getPassword())){
            log.error("用户名或密码错误...");
            throw new UnknownAccountException();
        }
        if (user.getState().equals(1)){
            log.error("用户已经被封禁...");
            throw new LockedAccountException();
        }
        log.info("用户 [" + username + "] 登录成功...");
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes(username), getName());
        return info;
    }

    /**
     * 重写方法,清除当前用户的的 授权缓存
     * @param principals
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写方法，清除当前用户的 认证缓存
     * @param principals
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 自定义方法：清除所有 授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 自定义方法：清除所有 认证缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 自定义方法：清除所有的  认证缓存  和 授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
