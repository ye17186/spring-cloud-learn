package com.yclouds.cloud.demo.zuul.fallback;

import com.yclouds.myhelper.web.error.code.BaseEnumError;
import com.yclouds.myhelper.web.response.ApiResp;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Component
public class ZuulFallbackProvider implements FallbackProvider {

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
                // 现代浏览器会自动采用UTF-8编码，直接使用MediaType.APPLICATION_JSON是没有问题的
                // 但是如果用postman等工具调试的话，会中文乱码
                // 因此这里暂时先采用已过时的MediaType.APPLICATION_JSON_UTF8
                // headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
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
