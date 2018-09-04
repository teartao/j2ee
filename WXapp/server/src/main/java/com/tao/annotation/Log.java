package com.tao.annotation;

import java.lang.annotation.*;

/**
 * @Author neotao
 * @Date 2018/9/4
 * @Version V0.0.1
 * @Desc
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    String description() default "";
}