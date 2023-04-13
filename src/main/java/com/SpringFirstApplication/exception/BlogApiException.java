package com.SpringFirstApplication.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException{


    public HttpStatus status;

    public String msg;

    public BlogApiException(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}

