package com.yclouds.cloud.demo.gateway;

import com.yclouds.myhelper.web.error.code.BaseEnumError;
import com.yclouds.myhelper.web.response.ApiResp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CloudDemoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudDemoGatewayApplication.class, args);
    }

    @RequestMapping("/fallback")
    public ApiResp fallback() {

        return ApiResp.retFail(BaseEnumError.SERVICE_ERROR);
    }
}
