package com.yclouds.cloud.demo.zuul.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * TestInterceptor
 *
 * @author yemeng-lhq
 * @version 2019/12/3 11:41
 */
@Component
public class TestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        System.out.println("==========================");
        return false;
    }
}
