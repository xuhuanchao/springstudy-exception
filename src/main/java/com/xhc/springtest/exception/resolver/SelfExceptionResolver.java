package com.xhc.springtest.exception.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 捕获全局异常，不处理ResponseStatusException ， 异常处理返回统一 exception视图
 */
@Order(value = -10)
public class SelfExceptionResolver implements HandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(SelfExceptionResolver.class);

    /**
     * 捕获全局异常
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        logger.error("处理异常：", e);
        e.printStackTrace();

        // 不处理 ResponseStatusException 异常
        if(e.getClass().getName().contains("ResponseStatusException")){
            return null;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "异常处理结果");
        mv.addObject("message", "处理异常的异常处理器是：" + this.getClass().getName());
        mv.addObject("exception", e.getMessage());

        mv.setViewName("exception");
        return mv;
    }
}
