package com.xhc.springtest.service;

import com.xhc.springtest.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class TestService {


    public String business() throws BusinessException {
        //do some code
        throw new BusinessException(-1, "手动抛出业务异常");
    }


    /**
     * 两个数相除
     * @param a
     * @param b
     * @return
     * @throws BusinessException
     */
    public Integer divide(Integer a, Integer b) throws BusinessException{
        if(a.equals(b)){
            throw new BusinessException(101, "a、b不能相等");
        }
        return a / b;
    }

}
