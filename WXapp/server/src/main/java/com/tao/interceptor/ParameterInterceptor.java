package com.tao.interceptor;

import com.alibaba.fastjson.JSON;
import com.tao.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Author neotao
 * @Date 2018/5/18
 * @Version V0.0.1
 * @Desc
 */
public class ParameterInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(ParameterInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

//        logger.info("param string : \n{}", JSON.toJSONString(object));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception e) throws Exception {
        if (object instanceof List || object instanceof Map) {
            Result<Object> result = new Result<>();
            result.setCode("0");
            result.setMsg("请求成功");
            result.setData(object);
            object = result;
        }
//        logger.info("result string : \n{}", JSON.toJSONString(object));
    }
}
