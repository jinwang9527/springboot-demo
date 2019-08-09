package com.firststringboot.common.config;

import com.firststringboot.common.filter.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AssemblyConfigurer extends WebMvcConfigurerAdapter {


    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new TestFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CorsInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/user/testToken", "/user/testRabbit", "/user/loginFtp", "/user/testUploadFile", "/user/getPath","/user/getUser");
        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/**");
    }


    @Bean
    public FilterRegistrationBean characterFilterRegistration() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new CharacterFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }


}
