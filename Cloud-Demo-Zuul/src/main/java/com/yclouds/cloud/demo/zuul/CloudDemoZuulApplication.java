package com.yclouds.cloud.demo.zuul;

import com.yclouds.myhelper.filter.EnableApiAccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableApiAccessFilter
public class CloudDemoZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudDemoZuulApplication.class, args);
    }

    @RequestMapping("/test")
    public String test() {
       return "i am ok";
    }

}
