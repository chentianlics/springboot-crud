package com.cs.springbootcrud.annotation;

import com.cs.springbootcrud.dto.MSkuEntityDto;
import com.cs.springbootcrud.model.MSkuEntity;
import com.cs.springbootcrud.service.DemoService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.Constants;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

/**
 * @ClassName DemoArgumentResolver
 * @Description TODO
 * @Auther chenss
 * @Date 2020/3/30 2:01 PM
 * @Version 1.0
 **/
@Component
public class DemoArgumentResolver  implements HandlerMethodArgumentResolver {

    @Autowired
    private DemoService demoService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //	return true;
        return methodParameter.hasMethodAnnotation(DemoArgument.class)||methodParameter.hasParameterAnnotation(DemoArgument.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        //临时写死
        Long id = 70L;
        MSkuEntity mSkuEntity = demoService.getById(id);
        MSkuEntityDto  mSkuEntityDto = new MSkuEntityDto();
        BeanUtils.copyProperties(mSkuEntity,mSkuEntityDto);
        return mSkuEntityDto;
    }
}
