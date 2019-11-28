package com.yclouds.cloud.demo.zuul.fallback;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.yclouds.myhelper.web.error.code.BaseEnumError;
import com.yclouds.myhelper.web.response.ApiResp;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Zuul服务降级处理
 *
 * @author ye17186
 * @version 2019/11/28 15:35
 */
@Component
public class ZuulFallbackProvider implements FallbackProvider {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String getRoute() {
        return "*"; // 对所有微服务降级
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {

        log.error("[Zuul路由出现异常] serviceId: {}", route, cause);
        return response(route);
    }

    private ClientHttpResponse response(String route) {

        return new ClientHttpResponse() {

            @Override
            @NonNull
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setAcceptCharset(Lists.newArrayList(Charsets.UTF_8));
                return headers;
            }

            @Override
            @NonNull
            public InputStream getBody() {
                ApiResp ret = ApiResp
                    .retFail(BaseEnumError.SERVICE_DOWN.getCode(),
                        BaseEnumError.SERVICE_DOWN.getMsg().replace("${serviceId}", route));
                return new ByteArrayInputStream(ret.toString().getBytes());
            }

            @Override
            @NonNull
            public HttpStatus getStatusCode() {
                return HttpStatus.OK;
            }

            @Override
            @NonNull
            public int getRawStatusCode() {
                return HttpStatus.OK.value();
            }

            @Override
            @NonNull
            public String getStatusText() {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {
            }
        };
    }
}
