package com.example.demo.config.security.common;

import lombok.Getter;

@Getter
public enum CommonResponse {

    SUCCESS(0, "Success"), FAIL(-1, "Fail");

    int code;
    String msg;

    CommonResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
