package com.afs.restfulapi.ControllerAdvice;

public class ErrorResponse {
    private Integer httpCode;
    private String msg;


    //todo
    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ErrorResponse(Integer httpCode, String msg) {
        this.httpCode = httpCode;
        this.msg = msg;
    }
}
