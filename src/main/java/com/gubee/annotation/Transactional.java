package com.gubee.annotation;


import java.lang.annotation.*;// Version.java
@Target({ElementType.METHOD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Transactional {

}
