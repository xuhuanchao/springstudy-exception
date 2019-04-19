package com.xhc.springtest.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "找不到资源!")
public class ResponseStatusException extends RuntimeException {

    private Integer code;

    public ResponseStatusException() {
    }

    public ResponseStatusException(Integer code, String message) {
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
