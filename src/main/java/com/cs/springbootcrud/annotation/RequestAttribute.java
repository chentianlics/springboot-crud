/**
 * 
 */
package com.cs.springbootcrud.annotation;

import java.lang.annotation.*;


/**
 * @description: :
 * 
 * @Param: 
 * @Return: 
 * @Author: chenss
 * @Date: 2020/3/30 1:49 PM
 */
@Target(ElementType.PARAMETER)  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface RequestAttribute {  
  
    /** 
     * The request attribute parameter to bind to. 
     */  
    String value() default "";
      
}  
