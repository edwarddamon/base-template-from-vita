package com.vita.common.exception.handler;

import com.vita.common.domain.Result;
import com.vita.common.domain.ResultCode;
import com.vita.common.domain.common.CommonConstant;
import com.vita.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;
import java.util.stream.Collectors;

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
     * 全局参数异常
     *
     * @param e 异常信息
     * @return 响应信息
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<Void> serviceExceptionHandler(MethodArgumentNotValidException e) {
        log.error("[全局异常处理][参数校验异常处理] 发生参数校验异常，原因: {}", e.getMessage(), e);
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors() && !CollectionUtils.isEmpty(bindingResult.getAllErrors())) {
            Set<String> messageSet = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toSet());
            message = StringUtils.join(messageSet, CommonConstant.SEMICOLON);
        }
        return Result.fail(ResultCode.PARAM_CHECK_EXCEPTION.getCode(),
                StringUtils.isNotBlank(message) ? message : ResultCode.PARAM_CHECK_EXCEPTION.getMessage()
        );
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
