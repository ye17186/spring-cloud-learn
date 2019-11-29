package com.yclouds.cloud.demo.service.a.feign.servicec;

import com.yclouds.cloud.demo.service.a.feign.ServiceId;
import com.yclouds.myhelper.web.response.ApiResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ServiceBFeignClient
 *
 * @author ye17186
 * @version 2019/11/28 17:41
 */
@FeignClient(value = ServiceId.SERVICE_C, fallbackFactory = ServiceCFallbackFactory.class)
public interface ServiceCFeignClient {

    @GetMapping("/demo/m1")
    ApiResp<String> demoM1();

    @GetMapping("/demo/m2")
    ApiResp<String> demoM2();
}
