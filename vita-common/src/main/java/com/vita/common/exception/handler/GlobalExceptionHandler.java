package com.vita.common.exception.handler;

import com.vita.common.domain.Result;
import com.vita.common.domain.ResultCode;
import com.vita.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @since 2023/05/30 11:15
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     *
     * @param e 异常信息
     * @return 响应信息
     */
    @ExceptionHandler(value = ServiceException.class)
    public Result<Void> serviceExceptionHandler(ServiceException e) {
        log.error("[全局异常处理][ServiceException] 发生业务异常，原因: {}", e.getMessage(), e);
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 其他异常
     *
     * @param e 异常信息
     * @return 响应信息
     */
    @ExceptionHandler(value = Exception.class)
    public Result<Void> exceptionHandler(Exception e) {
        log.error("[全局异常处理][Exception] 发生异常，原因: {}", e.getMessage(), e);
        return Result.fail(ResultCode.FAIL);
    }

    /**
     * 其他异常
     *
     * @param e 异常信息
     * @return 响应信息
     */
    @ExceptionHandler(value = Throwable.class)
    public Result<Void> throwableHandler(Throwable e) {
        log.error("[全局异常处理][Throwable] 发生异常，原因: {}", e.getMessage(), e);
        return Result.fail(ResultCode.FAIL);
    }

}
