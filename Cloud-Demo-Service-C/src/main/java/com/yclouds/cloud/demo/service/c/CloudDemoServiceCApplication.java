package com.yclouds.cloud.demo.service.c;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RestController
public class CloudDemoServiceCApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudDemoServiceCApplication.class, args);
    }

    @RequestMapping("/test2")
    public Map<String, String> test2() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "你好，iama");
        map.put("b", "你好，iamb");
        return map;
    }

}
