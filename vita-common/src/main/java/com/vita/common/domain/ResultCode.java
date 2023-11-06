package com.vita.common.domain;

import lombok.Getter;

/**
 * 存放响应信息
 */
@Getter
public enum ResultCode {

    /**
     * 成功状态码
     */
    SUCCESS(200, "OK"),

    /**
     * 失败状态码
     */
    FAIL(-1, "系统异常"),

    /**
     * 错误状态码
     */
    UNAUTHORIZED(401, "Unauthorized"),

    ;

    private final Integer code;

    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}