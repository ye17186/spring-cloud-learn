package com.yclouds.cloud.demo.service.b;

import com.yclouds.myhelper.filter.EnableApiAccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableApiAccessFilter
public class CloudDemoServiceBApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudDemoServiceBApplication.class, args);
    }

}
