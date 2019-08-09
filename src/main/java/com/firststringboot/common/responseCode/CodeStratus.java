package com.firststringboot.common.responseCode;

public enum CodeStratus {

    OK(0, "success"),

    FAIL(1, "fail"),

    INVALID_TOKEN(2, "token无效或过期"),

    NOT_AUTH(3, "无权限");


    int code;

    String message;

    CodeStratus(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
