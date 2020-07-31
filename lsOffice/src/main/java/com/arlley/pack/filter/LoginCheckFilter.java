package com.arlley.pack.filter;

import com.arlley.pack.entity.User;
import com.arlley.pack.exception.BusinessException;
import com.arlley.pack.exception.LoginException;
import com.arlley.pack.service.UserService;
import com.arlley.pack.vo.LoginVo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;

public class LoginCheckFilter extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object handler) throws Exception {

        String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        //检查有没有需要用户权限的注解
        if (token == null) {
            throw new LoginException("无token，请重新登录");
        }
        // 获取 token 中的 user id
        int userId;
        try {
            userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        } catch (JWTDecodeException j) {
            throw new BusinessException("401");
        }
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new LoginException("用户不存在，请重新登录");
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new LoginException("用户名密码错误");
        }
        return true;
    }
}
