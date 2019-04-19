package com.xhc.springtest.exception.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Component("GolbalExceptionResolver1")
public class GolbalExceptionResolver extends ExceptionHandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(GolbalExceptionResolver.class);
    private final Map exceptionHandlerMethodResolvers = new ConcurrentHashMap<>();



    @Override
    protected ServletInvocableHandlerMethod getExceptionHandlerMethod(HandlerMethod handlerMethod, Exception exception) {
        Class handlerType = handlerMethod.getBeanType();
        Method method = getExceptionHandlerMethodResolver(handlerType).resolveMethod(exception);
        if(method != null){
            return new ServletInvocableHandlerMethod(handlerMethod.getBean(), method);
        }
        try{
            method = getClass().getMethod("getFailedResult", new Class[] {Exception.class});
        }
        catch(Exception exception1) {
            exception1.printStackTrace();
        }
        return new ServletInvocableHandlerMethod(this, method);
    }

    private ExceptionHandlerMethodResolver getExceptionHandlerMethodResolver(Class handlerType){
        ExceptionHandlerMethodResolver resolver = (ExceptionHandlerMethodResolver)exceptionHandlerMethodResolvers.get(handlerType);
        if(resolver == null){
            resolver = new ExceptionHandlerMethodResolver(handlerType);
            exceptionHandlerMethodResolvers.put(handlerType, resolver);
        }
        return resolver;
    }


    @ResponseBody
    public Map getFailedResult(Exception e){
        logger.error("未知异常", e);

        Map result = new HashMap();
        result.put("code", 201);
        result.put("message", e.getClass() + ":" + e.getMessage());
        result.put("resolver", "GolbalExceptionResolver异常处理器");
        return result;
    }

}
