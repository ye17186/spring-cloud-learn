package com.yclouds.cloud.demo.service.a.feign.serviceb;

import com.yclouds.myhelper.web.response.ApiResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ServiceBFeignClient
 *
 * @author ye17186
 * @version 2019/11/28 17:41
 */
@FeignClient(value = "cloud-demo-service-b", fallbackFactory = ServiceBFallbackFactory.class)
public interface ServiceBFeignClient {

    @GetMapping("/demo/m1")
    ApiResp<String> demoM1();
}
