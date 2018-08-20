package com.gs.crms.webapi.controller;

import com.gs.crms.applycertify.contract.model.ApplyBasicInfo;
import com.gs.crms.applycertify.contract.model.ApplyBussinessHistory;
import com.gs.crms.applycertify.contract.model.ApplyInfo;
import com.gs.crms.applycertify.contract.model.CertificateApplyInfo;
import com.gs.crms.applycertify.contract.model.admin.ApplyInfoParam;
import com.gs.crms.applycertify.contract.model.search.ApplyPersonQuery;
import com.gs.crms.applycertify.contract.service.CertificateApplyService;
import com.gs.crms.common.annotation.LimitIPRequestAnnotation;
import com.gs.crms.common.configs.HttpError;
import com.gs.crms.common.model.ReturnBase;
import com.gs.crms.common.utils.JavaBeanValidationUtil;
import com.gs.crms.crimenotice.contract.model.CrimeRecordPrint;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengyali on 2018/1/12.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "申请业务查询使用 Api")
public class ApplicationSearchController {
    @Autowired
    private CertificateApplyService certificateApplyService;
    /**
     * 通过分析员ID获取分析任务
     * 申请详情
     *
     * @param analystId
     * @return
     */
    @RequestMapping(value = "/application/analysis/get/{analystId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分析员获取新个人申请分析任务", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<CertificateApplyInfo> getAnalysisTaskByAnalystId(@PathVariable String analystId) {
        CertificateApplyInfo certificateApplyInfo = null;
        if (StringUtils.isBlank(analystId)) {
            return new ResponseEntity<>(certificateApplyInfo, HttpStatus.BAD_REQUEST);
        }
        certificateApplyInfo = certificateApplyService.getAnalysisTaskByAnalystId(analystId);
        return new ResponseEntity<>(certificateApplyInfo, HttpStatus.OK);
    }
    /**
     * 通过申请ID查看申请详情
     *
     * @param applyId
     * @return
     */
    @RequestMapping(value = "/application/get/{applyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据ID查询申请(不包含公告)", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<CertificateApplyInfo> getApplyInfo(@PathVariable String applyId) {
        CertificateApplyInfo applyInfo = null;
        if (StringUtils.isBlank(applyId)) {
            return new ResponseEntity<>(applyInfo, HttpStatus.BAD_REQUEST);
        }
        applyInfo = certificateApplyService.getApplyInfo(applyId);
        return new ResponseEntity<>(applyInfo, HttpStatus.OK);
    }
    /**
     * 数据来源：人口库接口
     * 通过身份证ID和姓名模糊匹配获取申请人列表
     *
     * @return
     */
    @RequestMapping(value = "/application/personInfo/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "匹配人员进行回填", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<ApplyBasicInfo>> getApplyPersonInfoByQuery(@RequestBody ApplyPersonQuery applyPersonQuery) {
        List<ApplyBasicInfo> applyBasicInfoList = new ArrayList<>();
        if (applyPersonQuery != null && JavaBeanValidationUtil.validate(applyPersonQuery) && applyPersonQuery.validateData()) {
            applyBasicInfoList = certificateApplyService.getApplyPersonInfoByQuery(applyPersonQuery);
            return new ResponseEntity<>(applyBasicInfoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(applyBasicInfoList, HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * 查询用户当天办理的申请量
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/application/day/handle/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询用户当天办理的申请量", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<ReturnBase<List<ApplyInfo>>> getPersonalOneDayHandleApplication(@ApiParam(value = "工作人员ID") @PathVariable String id, @ApiParam(value = "页码") @RequestParam int pages, @ApiParam(value = "每页数量") @RequestParam int pageSize) {
        ReturnBase<List<ApplyInfo>> returnBase = new ReturnBase<>();
        if (StringUtils.isBlank(id)) {
            return new ResponseEntity<>(returnBase, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(certificateApplyService.getPersonalDayHandleApplication(id, pages, pageSize), HttpStatus.OK);
    }

    /**
     * 查询用户未完成的申请
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/application/undone/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询用户未完成的申请", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<ReturnBase<List<ApplyInfo>>> getUnCompleteApplicationByUserId(@ApiParam(value = "工作人员ID") @PathVariable String id, @ApiParam(value = "页码") @RequestParam int pages, @ApiParam(value = "每页数量") @RequestParam int pageSize,@ApiParam(value = " 申请状态") @RequestParam(required = false)  String applyStatus) {
        ReturnBase<List<ApplyInfo>> returnBase = new ReturnBase<>();
        if (StringUtils.isBlank(id)) {
            return new ResponseEntity<>(returnBase, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(certificateApplyService.getUnCompleteApplicationByUserId(id, pages, pageSize,applyStatus), HttpStatus.OK);
    }
    /**
     * 查询分析员需要重新分析的申请
     *
     * @param pages
     * @param pageSize
     * @param analystId
     * @return
     */
    @RequestMapping(value = "/application/reply/analysis/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询分析员需要重新分析的申请", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<ReturnBase<List<ApplyInfo>>> getNeedReplyAnalysisApplication(@ApiParam(value = "页码") @RequestParam int pages, @ApiParam(value = "每页数量") @RequestParam int pageSize, @ApiParam(value = "分析员Id") @RequestParam String analystId) {
        ReturnBase<List<ApplyInfo>> returnBase = new ReturnBase<>();
        if (StringUtils.isBlank(analystId)) {
            return new ResponseEntity<>(returnBase, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(certificateApplyService.getPersonalApplicationTask(pages, pageSize, analystId, ""), HttpStatus.OK);
    }

    /**
     * 查询审核员需要审核的申请
     *
     * @param pages
     * @param pageSize
     * @param auditorId
     * @return
     */
    @RequestMapping(value = "/audit/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询审核员需要审核的申请", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<ReturnBase<List<ApplyInfo>>> getNeedAuditApplicationById(@ApiParam(value = "页码") @RequestParam int pages, @ApiParam(value = "每页数量") @RequestParam int pageSize, @ApiParam(value = "审核员Id") @RequestParam String auditorId) {
        ReturnBase<List<ApplyInfo>> returnBase = new ReturnBase<>();
        if (StringUtils.isBlank(auditorId)) {
            return new ResponseEntity<>(returnBase, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(certificateApplyService.getPersonalApplicationTask(pages, pageSize, "", auditorId), HttpStatus.OK);
    }

    /**
     * 查询审核员已审核的申请
     *
     * @param applyInfoParam
     * @return
     */
    @RequestMapping(value = "/hasAudit/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询审核员已审核的申请", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<ReturnBase<List<ApplyInfo>>> getHasAuditApplicationById(@RequestBody ApplyInfoParam applyInfoParam) {
      ReturnBase<List<ApplyInfo>> returnBase = new ReturnBase<>();
      if (applyInfoParam != null&&!StringUtils.isBlank(applyInfoParam.getAuditPersonId())) {
      return new ResponseEntity<>(certificateApplyService.getPersonalApplicationHasTask(applyInfoParam), HttpStatus.OK);
      }
      return new ResponseEntity<>(returnBase, HttpStatus.BAD_REQUEST);
    }

    /**
     * 查询申请的详细操作历史
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/application/history/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询申请的详细操作历史", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<ApplyBussinessHistory>> getApplyOperatorHistory(@ApiParam(value = "申请ID") @PathVariable String id) {
        List<ApplyBussinessHistory> applyBussinessHistories = new ArrayList<>();
        if (StringUtils.isBlank(id)) {
            return new ResponseEntity<>(applyBussinessHistories, HttpStatus.BAD_REQUEST);
        }
        applyBussinessHistories = certificateApplyService.getApplyOperatorHistoryById(id);
        return new ResponseEntity<>(applyBussinessHistories, HttpStatus.OK);
    }

    /**
     * 根据申请id获取公告信息和犯罪信息
     * 用于打印犯罪记录等
     *
     * @return
     */
    @RequestMapping(value = "/application/notice/{applyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = " 根据申请id获取公告信息和犯罪信息", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<CrimeRecordPrint>> findNoticeByApplyId(@PathVariable(required = true) String applyId) {
        List<CrimeRecordPrint> list = new ArrayList<>();
        if (StringUtils.isBlank(applyId)) {
            return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
        }
        list = certificateApplyService.findNoticeByApplyId(applyId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 根据公民身份证号精确查询公民基本信息
     *
     * @return
     */
    @RequestMapping(value = "/crms/citizeninfo/byid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据身份证号查询公民基本信息", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<ApplyBasicInfo>> getCitizenInfoByID(@RequestParam(required = true) String certificateNumber) {
        List<ApplyBasicInfo> result = new ArrayList<>();
        if (StringUtils.isBlank(certificateNumber)) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        result = certificateApplyService.getCitizenInfoByID(certificateNumber);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 根据姓名模糊查询公民基本信息
     *
     * @return
     */
    @RequestMapping(value = "/crms/citizeninfo/byname", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据姓名查询公民基本信息", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<ApplyBasicInfo>> getCitizenInfoByName(@RequestParam(required = true) String firstName,
                                                                     @RequestParam(required = true) String lastName, @RequestParam(required = true) String sex) {
        List<ApplyBasicInfo> result = new ArrayList<>();
        if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName) || StringUtils.isBlank(sex)) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        result = certificateApplyService.getCitizenInfoByFullName(firstName, lastName, sex);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 根据证件编号和回执单号查询申请办理进度
     *
     * @param deliveryReceiptNumbr
     * @param certificateNumber
     * @return
     */
    @RequestMapping(value = "/application/schedule", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据证件编号和回执单号查询申请办理进度", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<Map<String, Object>>> findApplicationSchedule(@ApiParam(value = "回执单号") @RequestParam(required = false) String deliveryReceiptNumbr, @ApiParam(value = "证件编号") @RequestParam(required = false) String certificateNumber) {
        if (StringUtils.isEmpty(deliveryReceiptNumbr) && StringUtils.isEmpty(certificateNumber)) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
        List<Map<String, Object>> result = certificateApplyService.getPersonalApplicationListBySearch(deliveryReceiptNumbr, certificateNumber);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
