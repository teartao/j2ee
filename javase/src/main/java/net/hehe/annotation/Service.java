package net.hehe.annotation;

import java.lang.annotation.*;

/**
 * @Author neo·tao
 * @Date 2017/11/12
 * @Desc
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
    String value() default "";
}
