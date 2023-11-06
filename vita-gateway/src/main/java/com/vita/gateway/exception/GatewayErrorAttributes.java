package com.vita.gateway.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class GatewayErrorAttributes extends DefaultErrorAttributes {

    public static final String STATUS = "status";
    public static final String SUCCESS = "success";
    public static final String CODE = "code";
    public static final String MESSAGE = "message";
    public static final String PATH = "path";
    public static final String TIMESTAMP = "timestamp";

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {

        Throwable error = super.getError(request);

        MergedAnnotation<ResponseStatus> responseStatusAnnotation = MergedAnnotations
                .from(error.getClass(), MergedAnnotations.SearchStrategy.TYPE_HIERARCHY).get(ResponseStatus.class);
        HttpStatus errorStatus = determineHttpStatus(error, responseStatusAnnotation);

        // 必须设置 status, 否则会报错, 因为 DefaultErrorWebExceptionHandler 的 renderErrorResponse 方法会获取此属性
        Map<String, Object> errorAttributes = new LinkedHashMap<>(9);
        errorAttributes.put(STATUS, errorStatus.value());
        errorAttributes.put(SUCCESS, Boolean.FALSE);
        errorAttributes.put(CODE, errorStatus.value());
        errorAttributes.put(MESSAGE, error.getMessage());
        errorAttributes.put(PATH, request.path());
        errorAttributes.put(TIMESTAMP, OffsetDateTime.now());
        return errorAttributes;
    }

    private HttpStatus determineHttpStatus(Throwable error, MergedAnnotation<ResponseStatus> responseStatusAnnotation) {
        if (error instanceof ResponseStatusException) {
            return ((ResponseStatusException) error).getStatus();
        }
        return responseStatusAnnotation.getValue(CODE, HttpStatus.class).orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}