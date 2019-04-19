package com.xhc.springtest.exception.resolver;


import com.xhc.springtest.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Order(1)
public class GolbalControllerAdvice  {

    private static Logger logger = LoggerFactory.getLogger(GolbalControllerAdvice.class);


    /**
     * businessException 异常处理
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Map businessExceptionHanlder (HttpServletRequest request, Exception e) {
        logger.error("操作异常", e);
        e.printStackTrace();

        Map result = new HashMap();
        result.put("code", 111);
        result.put("message", "Exception=" + e.getClass() + ", message=" + e.getMessage());
        result.put("resolver", "GolbalControllerAdvice的 @ExceptionHandler(BusinessException.class) 异常处理");

        return result;
    }



    /**
     * 全局异常处理
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map excepitonHandler (HttpServletRequest request, Exception e) {
        logger.error("操作异常", e);
        e.printStackTrace();

        Map result = new HashMap();
        result.put("code", 112);
        result.put("message", "Exception=" + e.getClass() + ", message=" + e.getMessage());
        result.put("resolver", "GolbalControllerAdvice的excepitonHandler异常处理");

        return result;
    }




    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
