package com.sheva.parkinglotdemo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 登录异常统一处理
 * @Author Sheva
 * @Date 2020/12/2
 */
@Getter
public class LoginException extends RuntimeException {

    private Integer status = BAD_REQUEST.value();

    public LoginException(String msg){
        super(msg);
    }

    public LoginException(HttpStatus status, String msg){
        super(msg);
        this.status = status.value();
    }
}
