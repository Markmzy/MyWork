package com.markmzy.mywork.wx.config;

import com.markmzy.mywork.wx.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @title: ExceptionAdvice
 * @Author Zhiyue Ma
 * @Date: 7/18/21 18:05
 * @Version 1.0
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice
{
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e)
    {
        log.error("执行异常", e);
        if(e instanceof MethodArgumentNotValidException) //后端数据验证异常
        {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            return Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage();
        }
        else if(e instanceof MyException) //自定义异常
        {
            MyException exception = (MyException) e;
            return exception.getMsg();
        }
        else if(e instanceof UnauthorizedException) //未授权异常
        {
            return "你不具备相关权限";
        }
        else //普通异常
        {
            return "后端执行异常";
        }
    }
}
