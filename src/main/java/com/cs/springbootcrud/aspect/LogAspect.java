package com.cs.springbootcrud.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Aspect  //表明是一个切面类
@Component //将当前类注入到Spring容器内
public class LogAspect {



    //切入点，其中execution用于使用切面的连接点。使用方法：execution(方法修饰符(可选)
    // 返回类型 方法名 参数 异常模式(可选)) ，可以使用通配符匹配字符，*可以匹配任意字符。
    @Pointcut("execution(public * com.cs.springbootcrud.controller.*.*(..))")
    public void LogAspect(){}

    //环绕通知，就是可以在执行前后都使用，这个方法参数必须为ProceedingJoinPoint，proceed()方法就是被切面的方法，
    // 上面四个方法可以使用JoinPoint，JoinPoint包含了类名，被切面的方法名，参数等信息。
    @Around("LogAspect()")
    public Object deAround(ProceedingJoinPoint pjp) throws Throwable{

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;

        //获取到request
        HttpServletRequest request = sra.getRequest();

        //ip地址
        String ipaddress;
        if (request.getHeader("x-forwarded-for") == null) {
            ipaddress = request.getRemoteAddr();
        } else {
            ipaddress = request.getHeader("x-forwarded-for");
        }

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String queryString = request.getQueryString();
        Object[] args = pjp.getArgs();
        String params = "";
        //获取请求参数集合并进行遍历拼接
        if(args.length>0){
            if("POST".equals(method)){
                for(Object object:args){
                    Map map = getKeyAndValue(object);
                    PropertyFilter proFileter=new PropertyFilter() {
                        @Override
                        public boolean apply(Object object, String name, Object value) {
                            if(name.equalsIgnoreCase("request")){
                                //false表示tel字段将被排除在外
                                return false;
                            }
                            if(name.equalsIgnoreCase("sm")){
                                //false表示tel字段将被排除在外
                                return false;
                            }
                            return true;
                        }
                    };
                    if("{}".equals(params)){
                        params = "";
                    }
                    params = params+JSON.toJSONString(map,proFileter);
                }
            }else if("GET".equals(method)){
                params = queryString;
            }
        }

        log.info("请求地址:{},请求ip:{},请求类型:{},请求参数:{}",url,ipaddress,method,params);

        // result的值就是被拦截方法的返回值
//        Object result = pjp.proceed();
//        Gson gson = new Gson();
//        log.info("请求结束===返回值:" + gson.toJson(result));

        return pjp.proceed();
    }


    public static Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 得到类对象
        Class userCla = (Class) obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val;
            try {
                val = f.get(obj);
                // 得到此属性的值
                map.put(f.getName(), val);// 设置键值
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
