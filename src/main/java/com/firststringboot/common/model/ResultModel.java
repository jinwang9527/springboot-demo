package com.firststringboot.common.model;

import com.firststringboot.common.responseCode.CodeStratus;
import lombok.Data;

@Data
public class ResultModel<T> {

    private int code;


    private String message;


    private T data;


    public ResultModel(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultModel(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResultModel(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public static <T> ResultModel getSuccessResultModel(int code, String message, T data) {
        return new ResultModel<T>(code, message, data);
    }

    public static ResultModel getSuccessResultModel(int code, String message) {
        return new ResultModel(code, message);
    }

    public static <T> ResultModel getSuccessResultModel(int code, T data) {
        return new ResultModel<T>(code, data);
    }

    public static <T> ResultModel getSuccessResultModel(String message, T data) {
        return new ResultModel<T>(message, data);
    }

    public static <T> ResultModel getSuccessResultModel(T data) {
        return new ResultModel<T>(CodeStratus.OK.getCode(), CodeStratus.OK.getMessage(), data);
    }

    public static <T> ResultModel getSuccessResultModel() {
        return new ResultModel(CodeStratus.OK.getCode(), CodeStratus.OK.getMessage());
    }

    public static <T> ResultModel getErrorResultModel(int code, String message, T data) {
        return new ResultModel<T>(code, message, data);
    }

    public static ResultModel getErrorResultModel(int code, String message) {
        return new ResultModel(code, message);
    }

    public static <T> ResultModel getErrorResultModel(int code, T data) {
        return new ResultModel<T>(code, data);
    }

    public static <T> ResultModel getErrorResultModel(String message, T data) {
        return new ResultModel<T>(message, data);
    }

    public static <T> ResultModel getErrorResultModel(T data) {
        return new ResultModel<T>(CodeStratus.FAIL.getCode(), CodeStratus.FAIL.getMessage(), data);
    }

    public static <T> ResultModel getErrorResultModel() {
        return new ResultModel<T>(CodeStratus.FAIL.getCode(), CodeStratus.FAIL.getMessage());
    }
}
