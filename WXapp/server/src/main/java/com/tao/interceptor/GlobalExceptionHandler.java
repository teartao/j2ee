package com.tao.interceptor;

import com.tao.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Result<String> requestError(Exception ex) {
        Result<String> resultInfo = new Result<>();
        resultInfo.setMsg("参数或格式错误");
        resultInfo.setCode("400");
        logger.warn("400-参数或格式错误:{}",ex.getMessage());
        ex.printStackTrace();
        return resultInfo;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public Result<String> responseError(Exception ex) {
        Result<String> resultInfo = new Result<>();
        resultInfo.setMsg("系统错误");
        resultInfo.setCode("500");
        logger.warn("500-系统错误:{}",ex.getMessage());
        ex.printStackTrace();
        return resultInfo;
    }
}
