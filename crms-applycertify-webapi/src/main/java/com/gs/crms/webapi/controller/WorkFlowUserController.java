package com.gs.crms.webapi.controller;

import com.gs.crms.applycertify.contract.model.ApplyAndAnalystRelation;
import com.gs.crms.applycertify.contract.model.WaitApportion;
import com.gs.crms.applycertify.contract.service.WorkFlowUseService;
import com.gs.crms.common.annotation.LimitIPRequestAnnotation;
import com.gs.crms.common.configs.HttpError;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by zhangqiang on 2017/9/28.
 * 工作流服务需要使用的接口
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "工作流服务调用Api")
public class WorkFlowUserController {

    @Autowired
    private WorkFlowUseService workFlowUseService;


    /**
     * 保存等待激活表接口
     *
     * @param waitApportion
     */
    @RequestMapping(value = "/waitApportion/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "保存等待激活表接口", notes = "工作流服务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<Boolean> saveWaitApportion(@ApiParam(value = "等待激活表内容", required = true) @RequestBody WaitApportion waitApportion) {
        if (waitApportion == null) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        boolean result = workFlowUseService.saveWaitApportion(waitApportion);
        if (result) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 保存申请分析/审核任务和用户关联表
     *
     * @param applyAndAnalystRelation
     */
    @RequestMapping(value = "/applyAndAnalystRelation/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "保存申请分析/审核任务和用户关联表", notes = "工作流服务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<Boolean> saveApplyAnlyastAuditor(@ApiParam(value = "用户关联表内容", required = true) @RequestBody ApplyAndAnalystRelation applyAndAnalystRelation) {
        if (applyAndAnalystRelation == null) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        boolean result = workFlowUseService.saveApplyAnalystAuditor(applyAndAnalystRelation);
        if (result) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
