package com.gs.crms.webapi.controller;

import com.gs.crms.applycertify.contract.model.ApplyPriority;
import com.gs.crms.applycertify.contract.model.ApplyPurpose;
import com.gs.crms.applycertify.contract.model.CertificateType;
import com.gs.crms.applycertify.contract.service.CommonApplyService;
import com.gs.crms.common.annotation.LimitIPRequestAnnotation;
import com.gs.crms.common.configs.HttpError;
import com.gs.crms.common.enums.ApplyType;
import com.gs.crms.common.model.CareerInfo;
import com.gs.crms.common.model.MarriageInfo;
import com.gs.crms.common.model.SexInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhengyali on 2018/1/12.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "申请业务获取基础数据 Api")
public class ApplicationCommonController {
    @Autowired
    private CommonApplyService commonApplyService;
    /**
     * 获取证件类型
     *
     * getGroupListInfo method
     *
     * @return
     */
    @RequestMapping(value = "/application/certificateType/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取证件类型", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<CertificateType>> getCertificateTypeList(@ApiParam(value = "业务类型", required = false) @RequestParam(required = false)ApplyType applyType) {
        List<CertificateType> result = commonApplyService.getCertificateTypeListByType(applyType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    /**
     * 获取申请目的
     * getGroupListInfo method
     *
     * @return
     */
    @RequestMapping(value = "/application/purpose/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取申请目的", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<ApplyPurpose>> getApplyPurposeList() {
        List<ApplyPurpose> result = commonApplyService.getApplyPurposeList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    /**
     * 获取申请优先级
     * getApplyPriority method
     *
     * @return
     */
    @RequestMapping(value = "/application/priority/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取申请优先级", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<ApplyPriority>> getApplyPriority() {
        List<ApplyPriority> result = commonApplyService.getAllApplyPriority();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    /**
     * 获取学历
     */
    @RequestMapping(value = "/application/career/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取学历", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<CareerInfo>> getCareerInfo() {
        List<CareerInfo> result = commonApplyService.getAllCareerInfo();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    /**
     * 获取婚姻状态
     */
    @RequestMapping(value = "/application/marriage/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取婚姻状态", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<MarriageInfo>> getMarriage() {
        List<MarriageInfo> result = commonApplyService.getMarriage();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    /**
     * 获取性别
     */
    @RequestMapping(value = "/application/gender/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取性别", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<SexInfo>> getGender() {
        List<SexInfo> result = commonApplyService.getSexInfo();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
