package com.gs.crms.webapi.controller;

import com.gs.crms.common.annotation.LimitIPRequestAnnotation;
import com.gs.crms.common.configs.HttpError;
import com.gs.crms.statisticsanalysis.contract.model.ReportParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;
import com.gs.crms.statisticsanalysis.contract.service.PersonalApplyStatisticsanalysisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengyali on 2017/11/20.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "个人申请业务统计分析 Api")
public class PersonStatisticsAndAnalysisController {
    @Autowired
    private PersonalApplyStatisticsanalysisService personalApplyStatisticsanalysisService;

    /**
     * 个人业务申请统计,
     * 按年按月
     * 统计视角：时间数量
     *
     * @return
     */
    @RequestMapping(value = "/statistics/person/get/date/centerCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "按年/月/自定义时间或采集点 统计业务数量", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getPersonalApplyStatisticsByDate(@RequestBody ReportParamModel reportParamModel) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (reportParamModel != null) {
            reportRtnModels = personalApplyStatisticsanalysisService.getPersonalApplyStatisticsByTime(reportParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        }
        return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
    }

    /**
     * 根据职业或办理用途 统计业务数量
     * 统计视角：职业 办理用途
     *
     * @return
     */
    @RequestMapping(value = "/statistics/person/get/profession/purpose", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据职业或办理用途 统计业务数量", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getPersonalApplyStatisticsByApplyPurpose(@RequestBody ReportParamModel reportParamModel) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (reportParamModel != null) {
            reportRtnModels = personalApplyStatisticsanalysisService.getPersonalApplyStatisticsByProfessionAndPurpose(reportParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        } else
            return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
    }

    /**
     * 根据职业或办理结果 统计业务数量
     * 统计视角：职业 办理结果
     *
     * @return
     */
    @RequestMapping(value = "/statistics/person/get/profession/result", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据职业或办理结果 统计业务数量", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getPersonalApplyStatisticsByAnalysisResultId(@RequestBody ReportParamModel reportParamModel) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (reportParamModel != null) {
            reportRtnModels = personalApplyStatisticsanalysisService.getPersonalApplyStatisticsByProfessionAndResult(reportParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        } else
            return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
    }

    /**
     * 根据办理结果或办理用途 统计业务数量
     * 统计视角：办理用途 办理结果
     *
     * @return
     */
    @RequestMapping(value = "/statistics/person/get/purpose/result", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据办理用途和办理结果统计业务数量", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> geApplyStatisticsByPurposePAndResultId(@RequestBody ReportParamModel reportParamModel) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (reportParamModel != null && StringUtils.isNotEmpty(reportParamModel.getDateType())) {
            reportRtnModels = personalApplyStatisticsanalysisService.getPersonalApplyStatisticsByPurposeAndResult(reportParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        } else
            return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
    }
}
