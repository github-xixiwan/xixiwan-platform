package com.xixiwan.platform.module.common.rest;

public class RestResponse<T> {

    private boolean success;

    private String errorCode;

    private String errorMsg;

    private String msg;

    private T data;

    public RestResponse() {
    }

    public RestResponse(boolean success, String errorCode, String errorMsg, String msg, T data) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.msg = msg;
        this.data = data;
    }

    public static <T> RestResponse<T> success(String msg) {
        RestResponse<T> resp = new RestResponse<>();
        resp.setSuccess(true);
        resp.setMsg(msg);
        return resp;
    }

    public static <T> RestResponse<T> success(T data) {
        RestResponse<T> resp = new RestResponse<>();
        resp.setSuccess(true);
        resp.setData(data);
        return resp;
    }

    public static <T> RestResponse<T> success(String msg, T data) {
        RestResponse<T> resp = new RestResponse<>();
        resp.setSuccess(true);
        resp.setMsg(msg);
        resp.setData(data);
        return resp;
    }

    public static <T> RestResponse<T> failure(String errorMsg) {
        RestResponse<T> resp = new RestResponse<>();
        resp.setSuccess(false);
        resp.setErrorMsg(errorMsg);
        return resp;
    }

    public static <T> RestResponse<T> failure(String errorCode, String errorMsg) {
        RestResponse<T> resp = new RestResponse<>();
        resp.setSuccess(false);
        resp.setErrorCode(errorCode);
        resp.setErrorMsg(errorMsg);
        return resp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
  
