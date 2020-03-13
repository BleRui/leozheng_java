package net.canway.demo.mvc.controller;

public class Result {

    private String code;

    private String message;

    private Object result;

    public Result() {
        this.code = "200";
    }

    public Result(Object result) {
        this();
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
