package com.cyber.api.controller;

import com.cyber.common.domain.Result;
import com.cyber.common.domain.ResultCode;
import com.cyber.common.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @since 2023/06/01 17:43
 */
@Slf4j
@RestController
@RequestMapping(value = "/health")
@Api(tags = "健康检查接口")
public class HealthController {

    @ApiOperation(value = "健康检查接口", notes = "healthy | unhealthy")
    @GetMapping(value = {"check", "/check/{info}"})
    public Result<Void> healthCheck(@PathVariable(value = "info", required = false) String info) {
        if ("healthy".equals(info) || StringUtils.isEmpty(info)) {
            log.info("healthy check: {}", info);
            return Result.success();
        } else {
            log.error("healthy check: {}", info);
            throw new ServiceException(ResultCode.FAIL.getCode(), "application is unhealthy");
        }
    }

}
