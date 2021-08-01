package com.yiranzhaojiu.springmvc.springframework.build.annotation;


import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestController {
}
