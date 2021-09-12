package cn.dtmusic.api.filter;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import cn.dtmusic.api.entity.User;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
public class UserFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request,response);

        //如果isAuthenticated是false 证明不是被登陆过的 同时isRemembered为true
        //证明没被登陆过且通过记住我登陆的
        if(!subject.isAuthenticated()&&subject.isRemembered()){
            //获取session并判断session是否为空
            Session session = subject.getSession(true);
            if(session.getAttribute("loginUser")==null){
                //如果是空的才初始化
                User user = (User) subject.getPrincipal();
                //存入用户数据
                session.setAttribute("loginUser",user);
            }
        }
        //返回一个boolean值 判断用户是否被登陆过，或者是否有记住我
        return subject.isAuthenticated()||subject.isRemembered();
    }
}
