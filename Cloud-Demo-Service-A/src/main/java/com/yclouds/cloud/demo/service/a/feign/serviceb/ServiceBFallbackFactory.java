package com.yclouds.cloud.demo.service.a.feign.serviceb;

import com.yclouds.myhelper.feign.fallback.AbstractFeignFallback;
import com.yclouds.myhelper.web.response.ApiResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * ServiceBFallbackFactory
 *
 * @author ye17186
 * @version 2019/11/28 17:49
 */
@Slf4j
@Component
public class ServiceBFallbackFactory extends AbstractFeignFallback<ServiceBFeignClient> {

    @Override
    public ServiceBFeignClient createClient() {
        return new ServiceBFeignClient() {
            @Override
            public ApiResp<String> demoM1() {
                return response("demoM1");
            }

            @Override
            public ApiResp<Integer> demoM2() {
                return response("demoM2");
            }
        };
    }
}
