package com.yclouds.cloud.demo.service.a.controller;

import com.yclouds.cloud.demo.service.a.feign.serviceb.ServiceBFeignClient;
import com.yclouds.myhelper.web.response.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoController
 *
 * @author ye17186
 * @version 2019/11/28 15:27
 */
@Slf4j
@RestController("/demo")
public class DemoController {

    @Autowired
    ServiceBFeignClient serviceBFeignClient;

    @GetMapping("/m1")
    public ApiResp<String> method1() {
        return ApiResp.retOK("i am service a");
    }

    @GetMapping("/m2")
    public String method2() {

        ApiResp<String> bResult = serviceBFeignClient.demoM1();
        log.info(bResult.toString());
        return "i am service a";
    }

}
