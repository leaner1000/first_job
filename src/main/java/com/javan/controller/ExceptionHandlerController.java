package com.javan.controller;

import com.javan.entity.Status;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class ExceptionHandlerController{
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseBody
    public Status unauthorizedExceptionHandler(Exception e){
        Status s=new Status();
        s.setstatus(400);
        s.setMsg("没有权限");
        return s;
    }
    @ExceptionHandler({UnauthenticatedException.class})
    @ResponseBody
    public Status unauthenticatedExceptionHandler(Exception e){
        Status s=new Status();
        s.setstatus(400);
        s.setMsg("尚未登录");
        return s;
    }
    @ExceptionHandler({MultipartException.class})
    @ResponseBody
    public Status MultipartExceptionHandler(Exception e){
        Status s=new Status();
        s.setstatus(400);
        s.setMsg("上传失败");
        return s;
    }
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Status ExceptionHandler(Exception e){
        Status s=new Status();
        s.setstatus(400);
        s.setMsg("未知错误"+e.getClass().toString());
        return s;
    }
}
