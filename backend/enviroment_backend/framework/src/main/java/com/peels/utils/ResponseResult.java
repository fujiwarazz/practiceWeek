package com.peels.utils;

/**
 * @Author peelsannaw
 * @create 06/01/2023 16:34
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用的结果返回类
 * @author peelsannaw
 * @param <T>
 */
@SuppressWarnings("all")
public class ResponseResult<T> implements Serializable {

    private String host;

    private Integer code;

    private String errorMessage;

    private T data;

    protected ResponseResult() {
        this.code = 200;
    }

    protected ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    private ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.errorMessage = msg;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.errorMessage = msg;
    }

    public static  <T> ResponseResult<?> errorResult(int code, String msg) {
        ResponseResult<?> result = new ResponseResult<>();
        return result.error(code, msg);
    }

    public static  <T> ResponseResult<?> okResult(int code, String msg) {
        ResponseResult<?> result = new ResponseResult<>();
        return result.ok(code, null, msg);
    }

    public static <T> ResponseResult<?> okResult(T data) {
        ResponseResult result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.getErrorMessage());
        if(data!=null) {
            result.setData(data);
        }
        return result;
    }

    public static <T> ResponseResult<?> okResult() {

        return new ResponseResult<>(200,"ok",null);
    }

    public static ResponseResult<?> UseMap(){
        Map<String, Object> data = new HashMap<>();
        return new ResponseResult<>().ok(data);
    }

    @SuppressWarnings({"rawtypes","unchecked"})
    public ResponseResult<T>addData(String key,T value){
        Map data = (HashMap) this.getData();
        data.put(key,value);
        return this;
    }

    public static ResponseResult<?> errorResult(AppHttpCodeEnum enums){
        return setAppHttpCodeEnum(enums,enums.getErrorMessage());
    }

    public static ResponseResult<?> errorResult(AppHttpCodeEnum enums, String errorMessage){
        return setAppHttpCodeEnum(enums,errorMessage);
    }

    public static ResponseResult<?> setAppHttpCodeEnum(AppHttpCodeEnum enums){
        return okResult(enums.getCode(),enums.getErrorMessage());
    }

    private static ResponseResult<?> setAppHttpCodeEnum(AppHttpCodeEnum enums, String errorMessage){
        return okResult(enums.getCode(),errorMessage);
    }

    private ResponseResult<?> error(Integer code, String msg) {
        this.code = code;
        this.errorMessage = msg;
        return this;
    }

    private ResponseResult<?> ok(Integer code, T data) {
        this.code = code;
        this.data = data;
        return this;
    }

    private ResponseResult<?> ok(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.errorMessage = msg;
        return this;
    }

    private ResponseResult<?> ok(T data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }



}
