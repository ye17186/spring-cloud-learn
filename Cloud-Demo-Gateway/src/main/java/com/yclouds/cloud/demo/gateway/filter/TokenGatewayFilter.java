package com.yclouds.cloud.demo.gateway.filter;

import com.yclouds.myhelper.plugins.token.TokenService;
import com.yclouds.myhelper.utils.JsonUtils;
import com.yclouds.myhelper.web.error.code.IEnumError;
import com.yclouds.myhelper.web.response.ApiResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * TokenGatewayFilter
 *
 * @author ye17186
 * @version 2019/12/3 14:19
 */
@Component
public class TokenGatewayFilter implements GlobalFilter, Ordered {

    @Autowired
    TokenService tokenService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        IEnumError error = tokenService.valid(request);
        if (error != null) {
            return doNotRoute(exchange.getResponse(), error);
        } else {
            return chain.filter(exchange);
        }
    }

    private Mono<Void> doNotRoute(ServerHttpResponse response, IEnumError error) {

        ApiResp data = ApiResp.retFail(error);
        DataBuffer buffer = response.bufferFactory().wrap(JsonUtils.obj2Json(data).getBytes());
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
