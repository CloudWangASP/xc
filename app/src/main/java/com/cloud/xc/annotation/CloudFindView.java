package com.cloud.xc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by cloud on 2017/7/12.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface CloudFindView {

    int value();

}
