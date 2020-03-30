/**
* @FileName: ExceptionResolver.java
* @Package com.cta.platform.resolver
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-4 下午03:23:19
* @version V1.0
*/
package com.cs.springbootcrud.annotation;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

@Component
public class RequestAttributeArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		//	return true;
		return methodParameter.hasMethodAnnotation(RequestAttribute.class)||methodParameter.hasParameterAnnotation(RequestAttribute.class);
	}

	@Override
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
		Annotation[] paramAnns = methodParameter.getParameterAnnotations();

		for (Annotation paramAnn : paramAnns) {
			if (RequestAttribute.class.isInstance(paramAnn)) {
				RequestAttribute reqAttr = (RequestAttribute) paramAnn;
				HttpServletRequest httprequest = (HttpServletRequest) nativeWebRequest.getNativeRequest();
				Object result = httprequest.getAttribute(reqAttr.value());
				return result;
			}
		}
		return nativeWebRequest.getNativeRequest();
	}
}
