package com.tao.interceptor;

import com.tao.entity.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Result<String> requestError(Exception ex) {
        Result<String> resultInfo = new Result<>();
        resultInfo.setData("参数或格式错误");
        resultInfo.setCode("400");
        resultInfo.setMsg("异常信息：" + ex.getMessage());
        return resultInfo;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public Result<String> responseError(Exception ex) {
        Result<String> resultInfo = new Result<>();
        resultInfo.setData("系统错误");
        resultInfo.setCode("500");
        resultInfo.setMsg("异常信息：" + ex.getMessage());
        return resultInfo;
    }
}
