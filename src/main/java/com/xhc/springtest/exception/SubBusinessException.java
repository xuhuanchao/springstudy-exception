package com.xhc.springtest.exception;

public class SubBusinessException extends BusinessException {

    private String subCode = "sub";


    public SubBusinessException() {
    }

    public SubBusinessException(Integer code, String message) {
        super(code, message);
    }
}
