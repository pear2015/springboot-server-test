package com.gs.crms.webapi.controller;

import com.gs.crms.common.annotation.LimitIPRequestAnnotation;
import com.gs.crms.common.configs.HttpError;
import com.gs.crms.common.utils.VariableUtil;
import com.gs.crms.crimenotice.contract.model.*;
import com.gs.crms.crimenotice.contract.model.search.*;
import com.gs.crms.crimenotice.contract.service.CrimeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.HashMap;
import java.util.Map;


/**
 * 犯罪信息REST API
 * Created by tanjie on 2017/3/2.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "犯罪公告相关 Api")
public class CrimeController {


    @Autowired
    private CrimeService crimeService;

    /**
     * 保存公告和犯罪基本信息和人员信息
     * 判断公告录入状态为空不可录入
     *
     * @param crimeAndNoticeExtendInfo
     * @return
     */
    @RequestMapping(value = "/crms/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "保存公告和犯罪基本信息和人员信息", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<Map<String, Object>> saveCrimeInfo(@ApiParam(value = "公告和犯罪", required = true) @RequestBody CrimeAndNoticeExtendInfo
                                                                     crimeAndNoticeExtendInfo) {
        Map<String, Object> map = new HashMap<>();
        if (!crimeAndNoticeExtendInfo.validateDataOnAdd()) {
            map.put(VariableUtil.SUCCESS, false);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        map = crimeService.addCrimeAndNoticeInfo(crimeAndNoticeExtendInfo);
        if (Boolean.valueOf(map.get(VariableUtil.SUCCESS).toString())) {
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 更新公告和犯罪基本信息和人员信息
     * 判断公告录入状态为空不可录入
     *
     * @param crimeAndNoticeExtendInfo
     * @return
     */
    @RequestMapping(value = "/crms/reply/submit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "更新公告和犯罪基本信息和人员信息 可用作重新分析接口", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<Map<String, Object>> updateCrimeInfo(@ApiParam(value = "公告和犯罪", required = true) @RequestBody CrimeAndNoticeExtendInfo
                                                                       crimeAndNoticeExtendInfo) {
        Map<String, Object> map = new HashMap<>();
        if (!crimeAndNoticeExtendInfo.checkDataOnUpdate()) {
            map.put(VariableUtil.SUCCESS, false);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        map = crimeService.updateCrimeAndNoticeInfo(crimeAndNoticeExtendInfo);
        if (Boolean.valueOf(map.get(VariableUtil.SUCCESS).toString())) {
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 公告查询 查询可编辑优先级的公告列表
     *
     * @param noticeInfo
     * @return
     */
    @RequestMapping(value = "/crms/notice/priority/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "编辑优先级", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<Boolean> editPriority(@RequestBody NoticeInfo noticeInfo) {
        if (!noticeInfo.checkDataOnEditPriority()) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        boolean result = crimeService.editPriority(noticeInfo);
        if (result) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    /**
     * 审核员提交审核
     *
     * @return
     */
    @RequestMapping(value = "/notice/audit/submit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "审核员提交审核保存", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<Map<String, Object>> submitAudit(@RequestBody NoticeInfo noticeInfo) {
        Map<String, Object> map = new HashMap<>();
        if (!noticeInfo.checkDataOnSubmitAudit()) {
            map.put(VariableUtil.SUCCESS, false);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        map = crimeService.submitAudit(noticeInfo);
        if (Boolean.valueOf(map.get(VariableUtil.SUCCESS).toString())) {
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 公告详情页（根据公告id查询）
     * 不含待合并列表
     */
    @RequestMapping(value = "/crms/notice/detail/{noticeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "公告(列表点击使用)", notes = "业务系统使用")

    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<CrimeAndNoticeExtendInfo> findCrimeAndNoticeByNoticeId(@PathVariable String noticeId) {
        CrimeAndNoticeExtendInfo crimeAndNoticeExtendInfo = new CrimeAndNoticeExtendInfo();
        if (StringUtils.isBlank(noticeId)) {
            return new ResponseEntity<>(crimeAndNoticeExtendInfo, HttpStatus.BAD_REQUEST);
        }
        crimeAndNoticeExtendInfo = crimeService.findCrimeAndNoticeByNoticeId(noticeId);
        return new ResponseEntity<>(crimeAndNoticeExtendInfo, HttpStatus.OK);
    }

    /**
     * 公告详情页（根据公告id查询）
     * 含待合并列表
     */
    @RequestMapping(value = "/crms/notice/auditDetail/{noticeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "公告审核详情", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<CrimeAndNoticeExtendInfo> findAuditDetailByNoticeId(@PathVariable String noticeId) {
        CrimeAndNoticeExtendInfo crimeAndNoticeExtendInfo = new CrimeAndNoticeExtendInfo();
        if (StringUtils.isBlank(noticeId)) {
            return new ResponseEntity<>(crimeAndNoticeExtendInfo, HttpStatus.BAD_REQUEST);
        }
        crimeAndNoticeExtendInfo = crimeService.findAuditDetailByNoticeId(noticeId);
        return new ResponseEntity<>(crimeAndNoticeExtendInfo, HttpStatus.OK);
    }

    /**
     * 公告详情页（根据犯罪人Id查询）
     */
    @RequestMapping(value = "/crms/inquire/detail/{crimePersonId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据犯罪人员id查询公告详情", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<CrimeAndNoticeListInfo> findNoticeAndCrimeByPersonId(@PathVariable String crimePersonId) {
        CrimeAndNoticeListInfo crimeAndNoticeListInfo = new CrimeAndNoticeListInfo();
        if (StringUtils.isBlank(crimePersonId)) {
            return new ResponseEntity<>(crimeAndNoticeListInfo, HttpStatus.BAD_REQUEST);
        }
        crimeAndNoticeListInfo = crimeService.getNoticeListByCrimePersonId(crimePersonId);
        return new ResponseEntity<>(crimeAndNoticeListInfo, HttpStatus.OK);
    }


}
