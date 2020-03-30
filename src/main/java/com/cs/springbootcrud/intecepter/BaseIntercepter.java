package com.cs.springbootcrud.intecepter;

import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @description: 定义拦截器，对于请求头中含有springboot-crud的并且值为pass的进行拦截
 * 如果请求头中没有tokenId，那么重定向到登录，如果没有授权，则重定向到授权
 * 
 * @Param: 
 * @Return: 
 * @Author: chenss
 * @Date: 2020/3/30 1:03 PM
 */
@Component
@Slf4j
public class BaseIntercepter implements HandlerInterceptor {

    private static final String FAIl_LOGIN_URI = "/error/notLogin";
    private static final String NO_AUTH = "/error/noAuth";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        if (null != request.getHeader("springboot-crud") && request.getHeader("springboot-crud").equals("pass")){

            if (null == request.getHeader("tokenId") || request.getHeader("tokenId").equals("")){
                log.info("=====未登录=====");
                request.getRequestDispatcher(FAIl_LOGIN_URI).forward(request,response);
                return false;
            }
            if (null == request.getHeader("auth") || request.getHeader("auth").equals("")){
                log.info("=====未授权=====");
                request.getRequestDispatcher(NO_AUTH).forward(request,response);
                return false;
            }
        }

        //已经有logaspect统一打印日志，故此处不再需要
//        String uri = request.getRequestURI();
//        String method = request.getMethod();
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        Map<String, String[]> parameterMap = request.getParameterMap();
//
//        parameterMap.forEach((k, v) -> {
//                    stringBuilder.append(k + "=");
//                    for (String str : v) {
//                        stringBuilder.append(str + ",");
//                    }
//                }
//        );
//
//        String params = StringUtil.isNotEmpty(stringBuilder.toString()) ? stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1) : "";
//
//        log.info("===请求===uri:{}====method:{}=====params:{}" ,uri,method,params);

        return true;
    }

}
