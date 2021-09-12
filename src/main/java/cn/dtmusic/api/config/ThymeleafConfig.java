package cn.dtmusic.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ description:
 * @ date:      2020/10/1
 * @ time:      10:35
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
@Configuration
public class ThymeleafConfig implements WebMvcConfigurer {
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index").setViewName("index");
                registry.addViewController("/explore_topList").setViewName("topList");
                registry.addViewController("/friend").setViewName("friend");
                registry.addViewController("/user/home").setViewName("userHome");
                registry.addViewController("/user/fans").setViewName("userFans");
                registry.addViewController("/user/userInfo").setViewName("userInfo");
                registry.addViewController("/user/login").setViewName("userLogin");
                registry.addViewController("/user/register").setViewName("userRegister");
                registry.addViewController("/user/toUserLogin").setViewName("toUserLogin");
            }
        };
    }
}
