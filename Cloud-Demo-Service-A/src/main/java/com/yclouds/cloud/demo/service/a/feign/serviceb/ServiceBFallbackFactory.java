package com.yclouds.cloud.demo.service.a.feign.serviceb;

import com.yclouds.cloud.demo.service.a.feign.ServiceId;
import com.yclouds.myhelper.feign.fallback.AbstractFallback;
import feign.hystrix.FallbackFactory;
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
public class ServiceBFallbackFactory extends AbstractFallback implements
    FallbackFactory<ServiceBFeignClient> {

    @Override
    public ServiceBFeignClient create(Throwable cause) {

        log.error("[service b fallback]", cause);

        return () -> fallback(ServiceId.SERVICE_B, "demoM1");
    }
}
