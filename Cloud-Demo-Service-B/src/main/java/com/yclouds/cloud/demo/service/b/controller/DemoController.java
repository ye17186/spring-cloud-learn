package com.yclouds.cloud.demo.service.b.controller;

import com.yclouds.myhelper.web.annotation.YRestController;
import com.yclouds.myhelper.web.response.ApiResp;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * DemoController
 *
 * @author ye17186
 * @version 2019/11/28 15:27
 */
@YRestController("/demo")
public class DemoController {

    @GetMapping("/m1")
    public ApiResp<String> method1() {

        return ApiResp.retOK("i am service b#method1()");
    }

    @GetMapping("/m2")
    public ApiResp<String> method2() throws InterruptedException {

        System.out.println("start");
        Thread.sleep(15000);
        System.out.println("end");
        return ApiResp.retOK("i am service b#method2()");
    }

}
