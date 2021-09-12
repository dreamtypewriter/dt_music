package cn.dtmusic.api.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import cn.dtmusic.api.entity.User;
import cn.dtmusic.api.service.UserService;

//自定义的UserRealm   extends AuthorizingRealm
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了 =>授权doGetAuthorizationInfo");
        return null;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了 =>doGetAuthenticationInfo");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        //用户名 密码  数据库中取
        String userPhone = (String) userToken.getPrincipal();
        User user = userService.selectByPrimaryKey(userPhone);
        if(user==null){
            return null;//抛出异常  UnknownAccountException
        }else {
            Subject currentSubject = SecurityUtils.getSubject();
            Session session = currentSubject.getSession();
            session.setAttribute("loginUser",user);

            //可以加密 MD5：-----   MD5颜值加密---userName
            //密码认证 shiro做
            return new SimpleAuthenticationInfo(user,user.getUserPassword(),"");
        }
    }
}
