package com.gs.crms.businessmonitoring.controller;

import com.gs.crms.applycertify.contract.model.*;
import com.gs.crms.applycertify.contract.model.admin.ApplyInfoParam;
import com.gs.crms.applycertify.contract.model.admin.CertificateParamModel;
import com.gs.crms.businessmonitoring.contract.service.CertificateApplyForAdminService;
import com.gs.crms.common.annotation.LimitIPRequestAnnotation;
import com.gs.crms.common.configs.HttpError;

import com.gs.crms.common.model.ReturnBase;
import com.gs.crms.common.utils.JavaBeanValidationUtil;
import io.swagger.annotations.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhangqiang on 2017/9/28.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
//Api swagger注解
@Api(value = "/api/v1", description = "业务监控席业务 Api")
public class CertificateApplyForAdminController {
    Logger s = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CertificateApplyForAdminService certificateApplyForAdminService;

    /**
     * 申请管理管理员查询申请信息列表
     *
     * @param applyInfoParam
     * @return
     */
    @RequestMapping(value = "/application/admin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "申请管理管理员查询申请信息列表", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<ReturnBase<List<CertificateApplyInfo>>> getApplyInfo(@RequestBody ApplyInfoParam applyInfoParam) {
        return new ResponseEntity<>(certificateApplyForAdminService.getCertificateApplyInfo("", applyInfoParam, false), HttpStatus.OK);
    }

    /**
     * 申请管理管理员查询待调整的优先级列表
     *
     * @param applyInfoParam
     * @return
     */
    @RequestMapping(value = "/application/priority/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "申请管理管理员查询待调整的优先级列表", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<ReturnBase<List<CertificateApplyInfo>>> getApplyInfo(@ApiParam(value = "其他工作工作人员ID") @RequestParam String orgPersonId, @ApiParam(value = "查询参数") @RequestBody ApplyInfoParam applyInfoParam) {
        return new ResponseEntity<>(certificateApplyForAdminService.getCertificateApplyInfo(orgPersonId, applyInfoParam, true), HttpStatus.OK);
    }

    /**
     * 管理员修改申请信息优先级
     *
     * @param applyId
     * @param priority
     * @param modifyPersonId
     * @return
     */
    @RequestMapping(value = "/application/priority/update", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "管理员修改申请信息优先级", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<Boolean> updateApplyPriority(@RequestParam String applyId, @RequestParam String priority, @RequestParam String modifyPersonId) {
        if (StringUtils.isBlank(applyId) || StringUtils.isBlank(priority) || StringUtils.isBlank(modifyPersonId)) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        boolean result = certificateApplyForAdminService.updateApplyPriority(applyId, priority, modifyPersonId);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 管理员查询证书
     *
     * @param certificateParamModel
     * @return
     */
    @RequestMapping(value = "/credentials/admin/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "管理员查询证书", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<ReturnBase<List<CertificateExtendInfo>>> getCertificate(@ApiParam("其他工作人员ID") @RequestParam String orgPersonId, @ApiParam(value = "证书查询") @RequestBody CertificateParamModel certificateParamModel) {
        if (certificateParamModel != null && JavaBeanValidationUtil.validate(certificateParamModel)) {
            return new ResponseEntity<>(certificateApplyForAdminService.getCertificate(orgPersonId, certificateParamModel), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReturnBase<>(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 添加证书
     *
     * @param certificateInfo
     * @return
     */
    @RequestMapping(value = "/credentials/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "添加证书", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<CertificateInfo> addCertificate(@RequestBody CertificateInfo certificateInfo) {
        if (certificateInfo != null && JavaBeanValidationUtil.validate(certificateInfo)) {
            CertificateInfo certificate = certificateApplyForAdminService.addCertificateInfo(certificateInfo);
            return new ResponseEntity<>(certificate, certificate!=null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(new CertificateInfo(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 更新证书
     *
     * @param certificateInfo
     * @return
     */
    @RequestMapping(value = "/credentials/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "添加证书", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<Boolean> updateCertificate(@RequestBody CertificateInfo certificateInfo) {
        if (certificateInfo != null && JavaBeanValidationUtil.validate(certificateInfo)) {
            boolean result = certificateApplyForAdminService.updateCertificateInfo(certificateInfo);
            return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 通过申请Id查询证书
     *
     * @param applyId
     * @return
     */
    @RequestMapping(value = "/credentials/get/{applyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "添加证书", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<CertificateInfo> getCertificateByApplyInfoId(@PathVariable String applyId) {
        if (StringUtils.isNotBlank(applyId)) {
            return new ResponseEntity<>(certificateApplyForAdminService.getCertificateByApplyInfoId(applyId), HttpStatus.OK);
        }
        return new ResponseEntity<>(new CertificateInfo(), HttpStatus.BAD_REQUEST);
    }

}
