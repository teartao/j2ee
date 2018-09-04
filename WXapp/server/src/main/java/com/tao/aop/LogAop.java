package com.tao.aop;

import com.tao.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Author neotao
 * @Date 2018/9/4
 * @Version V0.0.1
 * @Desc
 */
@Aspect
@Component
public class LogAop {

    private static final Logger log = LoggerFactory.getLogger(LogAop.class);

    // 配置织入点
    @Pointcut("@annotation(com.tao.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 前置通知 用于拦截操作，在方法返回后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        handleLog(joinPoint, null);
    }

    /**
     * 拦截异常操作，有异常时执行
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e);
    }

    private void handleLog(JoinPoint joinPoint, Exception e) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            // 获得注解
            Log controllerLogAop = getAnnotationLog(joinPoint);
            if (controllerLogAop == null) {
                return;
            }
            // 获得方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            joinPoint.getKind();
            doLog(controllerLogAop.description(), className, methodName, new Object[]{});

        } catch (Exception exp) {
            // 记录本地异常日志
            log.error(">>>>异常>>>>");
            log.error(">>>>异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
        if (e instanceof NullPointerException) {
            e.printStackTrace();
            log.warn("{}", e.getMessage());
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private static Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

    private void doLog(String description, String className, String methodName, Object[] params) {
        for (Object param : params) {

        }
        log.info(">>>>{}:{}.{}();\n参数:", description, className, methodName);
    }
}
