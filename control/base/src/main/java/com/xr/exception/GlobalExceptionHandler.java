package com.xr.exception;

import com.xr.util.ResponseResult;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)//需要捕获
    @ResponseBody
    public ResponseResult defaultExceptionHandler(HttpServletRequest req,Exception e){
        ResponseResult result=new ResponseResult();
        result.setCode(20001);// 自定义错误指导返回前台
        // ResponseResult新增了message属性，用来统一向前台传递后台的处理消息
        result.setMessage("权限不足");
        return result;
    }
}
