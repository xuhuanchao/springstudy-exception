package com.xhc.springtest.exception.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SelfSimpleExceptionResolver extends SimpleMappingExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(SelfSimpleExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(
            HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex) {
        ModelAndView mv = super.doResolveException(request, response, handler, ex);
        if(mv != null){
            mv.addObject("title", "异常处理结果");
            mv.addObject("message", "处理异常的异常处理器是：" + this.getClass().getName());
        }else {
            return null;
        }
        return mv;
    }

}
