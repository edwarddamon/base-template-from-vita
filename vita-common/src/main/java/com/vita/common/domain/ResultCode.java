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
    FAIL(1000, "系统异常"),

    /**
     * 错误状态码
     */
    UNAUTHORIZED(401, "Unauthorized"),

    /**
     * 参数校验异常状态码
     */
    PARAM_CHECK_EXCEPTION(600, "param.check.exception"),

    ;

    private final Integer code;

    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}