package com.utils.tools.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * controller 增强器
 * @ControllerAdvice 拦截异常并统一处理
 */

@ControllerAdvice
public class commonExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(CustomException e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
