package com.yclouds.cloud.demo.service.b.controller;

import com.yclouds.myhelper.web.annotation.YRestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoController
 *
 * @author ye17186
 * @version 2019/11/28 15:27
 */
@YRestController("/demo")
public class DemoController {

    @GetMapping("/m2")
    public String sayHello() throws InterruptedException {

        System.out.println("start");
        Thread.sleep(15000);
        System.out.println("end");
        return "i am service b";
    }

}
