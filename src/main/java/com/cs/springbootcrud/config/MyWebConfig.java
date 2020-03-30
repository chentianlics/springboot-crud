package com.cs.springbootcrud.config;


import com.cs.springbootcrud.annotation.DemoArgumentResolver;
import com.cs.springbootcrud.intecepter.BaseIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;


/**
 * @description:自定义拦截和资源映射规则
 * 
 * @Param: 
 * @Return: 
 * @Author: chenss
 * @Date: 2020/3/30 1:34 PM
 */
@Configuration
public class MyWebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private BaseIntercepter  baseIntercepter;

    @Autowired
    private DemoArgumentResolver demoArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(
                //添加自定义的拦截器，拦截所有请求，排除基础api，可以添加多个自定义的拦截器
                baseIntercepter).addPathPatterns("/**")
                //登录不拦截
                .excludePathPatterns("/api/login")
        ;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //资源映射配置
        registry.addResourceHandler("/MP_**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //可以添加多个argumentResolvers
        argumentResolvers.add(demoArgumentResolver);
        //argumentResolvers.add(xxxArgumentResolver);
    }

}
