package com.gs.crms.webapi.controller;

import com.gs.crms.common.utils.VariableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gs.crms.applycertify.contract.model.*;
import com.gs.crms.applycertify.contract.service.CertificateApplyService;
import com.gs.crms.common.annotation.LimitIPRequestAnnotation;
import com.gs.crms.common.configs.HttpError;

import java.util.HashMap;
import java.util.Map;

/**
 * 个人申请和政府申请无犯罪证明 API
 * Created by zhangqiang on 2017/8/22.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "申请操作业务 Api")
public class CertificateApplyController {

    Logger s = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CertificateApplyService certificateApplyService;

    /**
     * 保存申请
     *
     * @param certificateApplyInfo
     */
    @RequestMapping(value = "/application/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "保存申请", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<ApplyInfo> saveApplyInfo(@ApiParam(value = "新录入申请相关信息", required = true) @RequestBody CertificateApplyInfo certificateApplyInfo) {
        ApplyInfo applyInfo = null;
        if (certificateApplyInfo != null && certificateApplyInfo.validateData()) {
            applyInfo = certificateApplyService.saveApplyInfo(certificateApplyInfo);
            if (applyInfo == null) {
                return new ResponseEntity<>(applyInfo, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>(applyInfo, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(applyInfo, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 分析席
     * 保存分析结果
     *
     * @param certificateApplyInfo
     */
    @RequestMapping(value = "/application/analysis/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "保存分析结果", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<Map<String, Object>> updateCertificateApplyInfo(@ApiParam(value = "分析结果", required = true) @RequestBody CertificateApplyInfo certificateApplyInfo) {
        Map<String, Object> map = new HashMap<>();
        if (certificateApplyInfo != null && certificateApplyInfo.validateData()) {
            map = certificateApplyService.submitAnalysisResult(certificateApplyInfo);
            return new ResponseEntity<>(map, Boolean.valueOf(map.get("success").toString()) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            map.put(VariableUtil.SUCCESS, false);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 审核席
     * 保存审核结果
     *
     * @param certificateApplyInfo
     * @return
     */
    @RequestMapping(value = "/application/audit/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "保存审核结果", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<Map<String, Object>> applyAuditSubmit(@ApiParam(value = "审核结果", required = true) @RequestBody CertificateApplyInfo certificateApplyInfo) {
        Map<String, Object> map = new HashMap<>();
        if (certificateApplyInfo != null && certificateApplyInfo.validateData()) {
             map = certificateApplyService.applyAuditSubmit(certificateApplyInfo);
            return new ResponseEntity<>(map, Boolean.valueOf(map.get("success").toString()) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            map.put(VariableUtil.SUCCESS, false);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }
}
