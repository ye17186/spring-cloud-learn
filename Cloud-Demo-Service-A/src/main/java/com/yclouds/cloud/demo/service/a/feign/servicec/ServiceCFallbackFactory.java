package com.yclouds.cloud.demo.service.a.feign.servicec;

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
public class ServiceCFallbackFactory extends AbstractFeignFallback implements
    FallbackFactory<ServiceCFeignClient> {

    @PostConstruct
    public void init() {
        setServiceId(ServiceId.SERVICE_C);
    }

    @Override
    public ServiceCFeignClient create(Throwable cause) {

        // 打印fallback异常
        printLog(cause);

        return new ServiceCFeignClient() {
            @Override
            public ApiResp<String> demoM1() {
                return fallbackServiceC("demoM1");
            }

            @Override
            public ApiResp<String> demoM2() {
                return fallbackServiceC("demoM2");
            }
        };
    }

    private <T> ApiResp<T> fallbackServiceC(String method) {
        return fallback(method);
    }
}
