package com.wyu.common.exception;

import com.wyu.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.UNAUTHORIZED)//返回没有权限异常状态码
    @ExceptionHandler(value = ShiroException.class)
    public Result Handler(ShiroException e){

        log.error("运行时异常：-----------------------{}",e);
        return Result.fail(401,e.getMessage(),null);

    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)//返回请求异常状态码
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result Handler(MethodArgumentNotValidException e){

        log.error("实体校验异常：-----------------------{}",e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.fail(objectError.getDefaultMessage());

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)//返回请求异常状态码
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result Handler(IllegalArgumentException e){

        log.error("Assert异常：-----------------------{}",e);
        return Result.fail(e.getMessage());

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)//返回请求异常状态码
    @ExceptionHandler(value = RuntimeException.class)
    public Result Handler(RuntimeException e){

        log.error("运行时异常：-----------------------{}",e);
        return Result.fail(e.getMessage());

    }

}
