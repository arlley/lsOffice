package com.arlley.pack.config;

import com.arlley.pack.constant.ApiResponse;
import com.arlley.pack.exception.BusinessException;
import com.arlley.pack.exception.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Object> handlerException(BusinessException e){
        ApiResponse<Object> result = new ApiResponse<>();
        result.setCode(HttpStatus.EXPECTATION_FAILED.value());
        result.setMessage(e.getMessage());
        return result;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Object> handlerLoginException(LoginException e){
        ApiResponse<Object> result = new ApiResponse<>();
        result.setCode(400);
        result.setMessage(e.getMessage());
        return result;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Object> handlerException(Exception e){
        ApiResponse<Object> result = new ApiResponse<>();
        result.setCode(500);
        result.setMessage(e.getMessage());
        return result;
    }
}
