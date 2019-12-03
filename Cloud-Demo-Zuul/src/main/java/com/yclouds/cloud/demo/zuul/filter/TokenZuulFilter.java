package com.yclouds.cloud.demo.zuul.filter;

import com.google.common.base.Charsets;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.yclouds.myhelper.constants.ZuulFilterType;
import com.yclouds.myhelper.plugins.token.TokenService;
import com.yclouds.myhelper.utils.JsonUtils;
import com.yclouds.myhelper.web.error.code.IEnumError;
import com.yclouds.myhelper.web.response.ApiResp;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

/**
 * TokenZuulFilter
 *
 * @author ye17186
 * @version 2019/12/3 12:36
 */
@Log4j2
@Setter
@Getter
// @Configuration
public class TokenZuulFilter extends ZuulFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    public String filterType() {
        return ZuulFilterType.pre.name();
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();

        HttpServletRequest request = context.getRequest();

        IEnumError error = tokenService.valid(request);
        if (error != null) {
            return doNotRoute(context, error);
        } else {
            return null;
        }
    }

    private Object doNotRoute(RequestContext context, IEnumError error) {
        context.setSendZuulResponse(false);
        context.setResponseStatusCode(HttpStatus.OK.value());
        write(context.getResponse(), ApiResp.retFail(error));
        return null;
    }

    private void write(HttpServletResponse response, ApiResp ret) {
        try {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(Charsets.UTF_8.name());
            response.getWriter().write(JsonUtils.obj2Json(ret));
        } catch (IOException e) {
            log.error("TokenZuulFilter write error.", e);
        }
    }
}
