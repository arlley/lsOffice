package com.arlley.pack.filter;

import com.arlley.pack.constant.ApiResponse;
import com.arlley.pack.exception.BusinessException;
import com.arlley.pack.exception.LoginException;
import com.arlley.pack.vo.LoginVo;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Configuration
public class LoginCheckFilter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        LoginVo loginVo = (LoginVo) request.getSession(true).getAttribute("loginVo");
//        if (Objects.isNull(loginVo)){
//            throw new LoginException("您还未登录 请先登录");
//        }
        return true;
    }
}
