package com.gs.crms.webapi.controller;

import com.gs.crms.common.annotation.LimitIPRequestAnnotation;
import com.gs.crms.common.configs.HttpError;
import com.gs.crms.statisticsanalysis.contract.model.ReportParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;
import com.gs.crms.statisticsanalysis.contract.service.GovApplyStatisticsanalysisService;
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
@Api(value = "/api/v1", description = "政府申请业务统计分析 Api")
public class GovStatisticsAndAnalysisController {
    @Autowired
    private GovApplyStatisticsanalysisService govApplyStatisticsanalysisService;

    /**
     * 政府业务申请统计
     * 按年按月 自定义时间 统计业务数量
     * 统计视角：时间数量
     *
     * @return
     */
    @RequestMapping(value = "/statistics/government/get/date/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "按年/月/自定义时间 统计业务数量", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getGovApplyStatisticsByDate(@RequestBody ReportParamModel reportParamModel) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (reportParamModel != null) {
            reportRtnModels = govApplyStatisticsanalysisService.getGovApplyStatisticsByTime(reportParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        }
        return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
    }

    /**
     * 政府业务申请统计
     * 按照政府类型和时间段统计
     * 统计视角：政府类型+时间段
     *
     * @return
     */
    @RequestMapping(value = "/statistics/government/get/date/org", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "政府类型+按年/月/自定义时间 统计业务数量", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getGovApplyStatisticsByOrg(@RequestBody ReportParamModel reportParamModel) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (reportParamModel != null) {
            reportRtnModels = govApplyStatisticsanalysisService.getGovApplyStatisticsByOrg(reportParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        }
        return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
    }

    /**
     * 政府业务申请统计
     * 按照政府类型和办理用途统计
     * 统计视角：政府类型 +办理用途
     *
     * @return
     */
    @RequestMapping(value = "/statistics/government/get/org/purpose", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "政府类型 +办理用途 统计业务数量", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getGovApplyStatisticsByApplyPurpose(@RequestBody ReportParamModel reportParamModel) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (reportParamModel != null) {
            reportRtnModels = govApplyStatisticsanalysisService.getGovApplyStatisticsByOrgAndPurpose(reportParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        }
        return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
    }

    /**
     * 政府类型 +办理结果
     *
     * @param reportParamModel
     * @return 申请结果
     */
    @RequestMapping(value = "/statistics/government/get/org/result", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "政府类型 +办理结果业务数量", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getGovApplyStatisticsByAnalysisResultId(@RequestBody ReportParamModel reportParamModel) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (reportParamModel != null && StringUtils.isNotEmpty(reportParamModel.getDateType())) {
            reportRtnModels = govApplyStatisticsanalysisService.getGovApplyStatisticsByOrgAndResult(reportParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        }
        return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
    }
    /**
     * 办理用途 +办理结果
     *
     * @param reportParamModel
     * @return 申请结果
     */
    @RequestMapping(value = "/statistics/government/get/purpose/result", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取政府申请结果业务数量", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getGovApplyStatisticsByPurposeAndResultId(@RequestBody ReportParamModel reportParamModel) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (reportParamModel != null && StringUtils.isNotEmpty(reportParamModel.getDateType())) {
            reportRtnModels = govApplyStatisticsanalysisService.getGovApplyStatisticsByPurposeAndResult(reportParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        }
        return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
    }
}
