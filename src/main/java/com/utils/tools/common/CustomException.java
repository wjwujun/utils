package com.utils.tools.common;


/*
* 自定义异常
* */

public class CustomException  extends RuntimeException{
    private Integer code;
    private String  msg;

    public CustomException(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
