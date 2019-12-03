package com.yclouds.cloud.demo.zuul.config;

import com.yclouds.cloud.demo.zuul.interceptor.TestInterceptor;
import com.yclouds.myhelper.plugins.token.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfig
 *
 * @author yemeng-lhq
 * @version 2019/12/3 11:53
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    TestInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**");

    }

}
