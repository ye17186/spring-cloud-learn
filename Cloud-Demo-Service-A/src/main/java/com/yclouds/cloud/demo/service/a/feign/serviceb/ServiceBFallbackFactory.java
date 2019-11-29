package com.yclouds.cloud.demo.service.a.feign.serviceb;

import com.yclouds.cloud.demo.service.a.feign.ServiceId;
import com.yclouds.myhelper.feign.fallback.AbstractFeignFallback;
import com.yclouds.myhelper.web.response.ApiResp;
import feign.hystrix.FallbackFactory;
import javax.annotation.PostConstruct;
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
public class ServiceBFallbackFactory extends AbstractFeignFallback implements
    FallbackFactory<ServiceBFeignClient> {

    @PostConstruct
    public void init() {
        setServiceId(ServiceId.SERVICE_B);
    }

    @Override
    public ServiceBFeignClient create(Throwable cause) {

        // 打印fallback异常
        printLog(cause);

        return new ServiceBFeignClient() {
            @Override
            public ApiResp<String> demoM1() {
                return fallbackServiceB("demoM1");
            }

            @Override
            public ApiResp<String> demoM2() {
                return fallbackServiceB("demoM2");
            }
        };
    }

    private <T> ApiResp<T> fallbackServiceB(String method) {
        return fallback(method);
    }
}
