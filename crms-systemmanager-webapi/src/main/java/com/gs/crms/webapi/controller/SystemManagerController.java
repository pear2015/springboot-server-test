package com.gs.crms.webapi.controller;

import com.gs.crms.common.annotation.LimitIPRequestAnnotation;
import com.gs.crms.common.configs.HttpError;
import com.gs.crms.common.utils.JavaBeanValidationUtil;
import com.gs.crms.systemmanager.contract.model.LoginLog;
import com.gs.crms.systemmanager.contract.service.SystemManagerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhangqiang on 2017/11/2.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "系统管理 Api")
public class SystemManagerController {

    @Autowired
    private SystemManagerService systemManagerService;

    /**
     * 记录用户登陆日志
     *
     * @param loginLog
     * @return
     */
    @RequestMapping(value = "/loginlog/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "记录用户登陆日志", notes = "记录用户登陆日志")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<String> saveLoginLog(@ApiParam(value = "用户登陆日志", required = true) @RequestBody LoginLog loginLog) {

        if (loginLog != null && JavaBeanValidationUtil.validate(loginLog)) {
            return new ResponseEntity<>(systemManagerService.loginInsert(loginLog), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 记录用户登出日志
     *
     * @param loginLog
     * @return
     */
    @RequestMapping(value = "/loginlog/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "记录用户登出日志", notes = "记录用户登出日志")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<Boolean> saveLoginOutLog(@ApiParam(value = "用户登陆日志", required = true) @RequestBody LoginLog loginLog) {
        if (loginLog != null && JavaBeanValidationUtil.validate(loginLog)) {
            boolean result = systemManagerService.loginOutUpdate(loginLog);
            if (result) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
