package com.xhc.springtest.exception;

public class BusinessException extends RuntimeException {

    private Integer code;

    public BusinessException() {
        super();
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
