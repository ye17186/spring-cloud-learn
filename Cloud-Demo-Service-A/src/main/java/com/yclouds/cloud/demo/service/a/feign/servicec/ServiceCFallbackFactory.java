package com.yclouds.cloud.demo.service.a.feign.servicec;

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
public class ServiceCFallbackFactory extends AbstractFeignFallback<ServiceCFeignClient> {

    @Override
    public ServiceCFeignClient createClient() {

        return new ServiceCFeignClient() {
            @Override
            public ApiResp<String> demoM1() {
                return response("demoM1");
            }

            @Override
            public ApiResp<String> demoM2() {
                return response("demoM2");
            }
        };
    }
}
