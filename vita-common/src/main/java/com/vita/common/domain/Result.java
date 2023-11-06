package com.vita.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * @since 2023/05/30 10:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Result<T> {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 响应 code 码值
     */
    private Integer code;

    /**
     * 响应提示信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 成功响应
     *
     * @return 响应信息
     */
    public static Result<Void> success() {
        return new Result<Void>()
                .setSuccess(Boolean.TRUE)
                .setCode(ResultCode.SUCCESS.getCode())
                .setMessage(ResultCode.SUCCESS.getMessage());
    }

    /**
     * 成功响应（带响应参数）
     *
     * @param data 响应参数
     * @param <T>  泛型
     * @return 响应
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>()
                .setSuccess(Boolean.TRUE)
                .setCode(ResultCode.SUCCESS.getCode())
                .setMessage(ResultCode.SUCCESS.getMessage())
                .setData(data);
    }

    /**
     * 异常响应
     *
     * @param code code
     * @param msg  msg
     * @return 响应信息
     */
    public static Result<Void> fail(Integer code, String msg) {
        return new Result<Void>()
                .setSuccess(Boolean.FALSE)
                .setCode(code)
                .setMessage(msg);
    }

    /**
     * 异常响应
     *
     * @param resultCode resultCode
     * @return 响应信息
     */
    public static Result<Void> fail(ResultCode resultCode) {
        return new Result<Void>()
                .setSuccess(Boolean.FALSE)
                .setCode(resultCode.getCode())
                .setMessage(resultCode.getMessage());
    }

    /**
     * 判断是否响应成功
     *
     * @return 判断结果
     */
    public boolean isSuccess() {
        return Objects.equals(code, ResultCode.SUCCESS.getCode());
    }

}
