package cn.dtmusic.api.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import cn.dtmusic.api.filter.UserFormAuthenticationFilter;
import cn.dtmusic.api.realm.UserRealm;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //ShiroFilterFactoryBean 3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro的内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *    常用的过滤器：
         *       anon: 无需认证（登录）可以访问
         *       authc: 必须认证才可以访问
         *       user: 如果使用rememberMe的功能可以直接访问
         *       perms： 该资源必须得到资源权限才可以访问
         *       role: 该资源必须得到角色权限才可以访问
         */
        //拦截
        Map<String, String> filterMap = new LinkedHashMap<>();

        //授权，正常情况下，没有授权会跳转到未授权页面
//        filterMap.put("/user/add","perms[user:add]");

//        filterMap.put("/user/add","authc");
//        filterMap.put("/user/update","authc");
//        filterMap.put("/user/login","anon");//解除登录界面拦截
//        filterMap.put("/assets/**","anon");//解除静态资源拦截
//        filterMap.put("/userController/*","anon");//解除发送请求的拦截
        filterMap.put("/myMusic/mySongList","authc");
        filterMap.put("/myMusic/playList","authc");
        filterMap.put("/user/message","authc");
        filterMap.put("/friend","authc");
        filterMap.put("/user/home","authc");
        filterMap.put("/user/updateUserInfo","authc");

        //添加user过滤器
        /*filterMap.put("/index","user");// /index的请求只要使用rememberMe功能就可以访问了*/
//        filterMap.put("/myMusic/playList","user");
//        filterMap.put("/friend","user");
//        filterMap.put("/user/home","user");
//        filterMap.put("/user/updateUserInfo","user");

        //添加自定义过滤器
        filterMap.put("/","userFilter");
        /*filterMap.put("/index","userFilter");*/
        filterMap.put("/myMusic/playList","userFilter");
        filterMap.put("/myMusic/mySongList","userFilter");
        filterMap.put("/user/message","userFilter");
        filterMap.put("/friend","userFilter");
        filterMap.put("/user/home","userFilter");
        filterMap.put("/user/updateUserInfo","userFilter");

        bean.setFilterChainDefinitionMap(filterMap);
        //设置登陆的请求
        bean.setLoginUrl("/user/toUserLogin");
        //设置未授权页面
//        bean.setUnauthorizedUrl("/noauth");


        //把自定义的filter添加到shiro过滤器列表
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("userFilter",userFormAuthenticationFilter());
        bean.setFilters(filters);

        return bean;
    }
    //创建一个自定义的过滤器
    @Bean(name = "userFilter")
    public UserFormAuthenticationFilter userFormAuthenticationFilter(){
        return new UserFormAuthenticationFilter();
    }


    //DefaultWebSecurityManager 2
    @Bean(name ="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm,CookieRememberMeManager manager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);//userRealm被spring接管  需要@Qualifier
        //关联rememberMeManager
        securityManager.setRememberMeManager(manager);
        return securityManager;
    }

    //创建realm对象   需要自定义类1.
    @Bean //也可以@Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }


    /**
     * cookie对象;会话Cookie模板 ,默认为: JSESSIONID 问题: 与SERVLET容器名冲突,重新定义为sid或rememberMe，自定义
     * 创建cookie
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：
        //setcookie()的第七个参数
        //设为true后，只能通过http访问，javascript无法访问
        //防止xss读取cookie 设置制度模型-只能通过http访问cookie，js不能
        simpleCookie.setHttpOnly(true);
        /*simpleCookie.setPath("/");*/
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }
    /**
     * cookie管理对象;记住我功能,rememberMe管理器
     * 创建CookieManger
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(SimpleCookie simpleCookie){
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCookie(simpleCookie);;
        return manager;
    }

    //整合ShiroDialect：用来整合shiro和thymeleaf
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }








}
