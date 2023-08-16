package com.eprobj.config;

import com.eprobj.entity.Role;
import com.eprobj.entity.User;
import com.eprobj.mapper.RoleMapper;
import com.eprobj.service.UserService;
import com.eprobj.utill.SpringContextHolder;
import com.eprobj.utill.UserUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName UserRealm
 * @Description TODO
 * @Author kangjian
 * @Date 2019/9/14 17:38
 * @Version 1.0
 **/

public class UserRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        User curruser = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<Role> roles = SpringContextHolder.getBean(RoleMapper.class).listByUserName(curruser.getUserName());
        Set<String> roleNames = roles.stream().map(Role::getRoleName).collect(Collectors.toSet());
        authorizationInfo.setRoles(roleNames);
        authorizationInfo.addRoles(roleNames);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        User user = userService.queryInfoByUsername(username);
        logger.info("----->>userInfo="+user.getUserName()+"---"+user.getPassword());
        if(user == null) {
            //没找到帐号
            throw new AccountException("帐号或密码不正确！");
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户名
                user,
                user.getPassword(),
                getName()
        );
        UserUtil.setUserSession(user);  //设置user信息
        return authenticationInfo;
    }

    /**
     * 重写缓存key，否则集群下session共享时，会重复执行doGetAuthorizationInfo权限配置
     */
    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) principals;
        Object object = principalCollection.getPrimaryPrincipal();

        if (object instanceof User) {
            User user = (User) object;

            return "authorization:cache:key:users:" + user.getUserName();
        }

        return super.getAuthorizationCacheKey(principals);
    }
}
