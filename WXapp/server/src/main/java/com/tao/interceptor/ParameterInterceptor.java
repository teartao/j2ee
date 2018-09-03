package com.tao.interceptor;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        logger.info("param string : \n{}", JSON.toJSONString(object));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception e) throws Exception {
        logger.info("result string : \n{}", JSON.toJSONString(object));
    }
}
