package com.xhc.springtest.controlloer;

import com.xhc.springtest.exception.BusinessException;
import com.xhc.springtest.exception.ResponseStatusException;
import com.xhc.springtest.exception.SubBusinessException;
import com.xhc.springtest.service.TestService;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private TestService testService;


    /**
     * 测试controller 抛出异常时spring框架的异常处理功能
     * 根据入参产生不同种类的异常
     * @param var
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getError/{var}")
    @ResponseBody
    public String getError(@PathVariable("var") String var) throws Exception {
        Assert.notNull(var, "参数name不能为空");


        if(var.equals("1")){
            throw new NullPointerException("空指针异常");
        }
        else if(var.equals("2")){
            throw new IllegalArgumentException("参数异常");
        }
        else if(var.equals("3")){
            throw new IOException("io异常");
        }
        else if(var.equals("4")){
            throw new SubBusinessException(1001, "子业务异常");
        }
        else if(var.equals("5")){
            //抛出 @ResponseStatus 注解的异常，会被ResponseStatusExceptionResolver 进行异常处理
            throw new ResponseStatusException(123, "错误信息，not found");
        }
        else if(var.equals("6")){
            throw new HttpRequestMethodNotSupportedException("testMethod");
        }
        else {
            throw new BusinessException(1000, "默认业务异常");
        }
    }


    /**
     * 内部 NullPointerException  异常处理
     * @param e
     * @return
     */
    @ExceptionHandler({NullPointerException.class})
    @ResponseBody
    public Map resolverNullPointerException(IllegalArgumentException e){
        Map result = new HashMap();
        result.put("code", 101);
        result.put("message", e.getMessage());
        result.put("resolver", "TestController自身内部的 NullPointerException 异常处理");
        return result;
    }



    /**
     * ResponseStatusExceptionResolver 异常处理器测试 1
     * 抛出 @ResponseStatus 注解的异常，会被ResponseStatusExceptionResolver 进行异常处理
     * @return
     */
    @RequestMapping(value = "/testResponseStatusExceptionResolver1")
    public Map testResponseStatusExceptionResolver1(){
        throw new ResponseStatusException(123, "错误信息，not found");
    }


    /**
     * ResponseStatusExceptionResolver 异常处理器测试 2
     * 通过  @ResponseStatus 注解的方法抛出异常时，会被ResponseStatusExceptionResolver 进行全局异常处理
     * @return
     */
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "没有授权!")
    @RequestMapping(value = "/testResponseStatusExceptionResolver2")
    public Map testResponseStatusExceptionResolver2(){
        throw new RuntimeException("错误与信息，没有授权");
    }


    /**
     * 测试 DefaultHandlerExceptionResolver 异常处理类
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/defaultHandler")
    public String defaultHandler() throws Exception{
        throw new HttpRequestMethodNotSupportedException("defaultHandler");
    }








//    @RequestMapping(value = "/divide", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ResponseBody
//    public String divide(Integer a, Integer b){
//        Assert.notNull(a, "参数a不能为空");
//        Assert.notNull(b, "参数b不能为空");
//
//        Integer result = null;
//        result = testService.divide(a, b);
//        return "a / b = " + result;
//    }

//    @RequestMapping(value = "/business")
//    @ResponseBody
//    public String business() {
//        testService.business();
//        return "success";
//    }





}
