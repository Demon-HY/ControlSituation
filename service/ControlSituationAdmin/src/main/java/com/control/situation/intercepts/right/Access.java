package com.control.situation.intercepts.right;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018/4/8 0008.
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Access {

    /**
     * 模块 URL，用来记录菜单级别的权限
     */
    String moduleUrl() default "";

}
