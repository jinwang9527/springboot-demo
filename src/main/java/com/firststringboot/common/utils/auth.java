package com.firststringboot.common.utils;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, PACKAGE, PARAMETER, TYPE})
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface auth {



    String permissions() default "";


}
