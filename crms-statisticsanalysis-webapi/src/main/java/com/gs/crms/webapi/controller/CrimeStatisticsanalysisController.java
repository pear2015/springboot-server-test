package com.gs.crms.webapi.controller;

import com.gs.crms.common.annotation.LimitIPRequestAnnotation;
import com.gs.crms.common.configs.HttpError;
import com.gs.crms.statisticsanalysis.contract.model.ReportCrimeParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;
import com.gs.crms.statisticsanalysis.contract.service.CrimeInfoStatisticsAnalysisService;
import com.gs.crms.statisticsanalysis.contract.service.CrimeNoticeStatisticsAnalysisService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzuzhi on 2017/11/1.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "犯罪和公告相关业务统计分析业务 Api")
public class CrimeStatisticsanalysisController {

    @Autowired
    private CrimeInfoStatisticsAnalysisService crimeInfoStatisticsAnalysisService;

    @Autowired
    private CrimeNoticeStatisticsAnalysisService crimeNoticeStatisticsAnalysisService;


    /**
     * 犯罪公告统计
     * 按年/月/自定义时间获取每个法院犯罪公告数量
     * @param reportCrimeParamModel
     * @return
     */
    @RequestMapping(value = "/statistics/crimeNotice/get/dateType/court", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "按年/月/自定义时间获取每个法院犯罪公告数量", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getCrimeNoticeStatisticsByDate(@RequestBody  ReportCrimeParamModel reportCrimeParamModel) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (reportCrimeParamModel != null) {
            reportRtnModels = crimeNoticeStatisticsAnalysisService.getCrimeNoticeStatisticsByDate(reportCrimeParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        }
        return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
    }

    /**
     * 犯罪区域和犯罪类型
     * @param reportCrimeParamModel
     * @return
     */
    @RequestMapping(value = "/statistics/crime/region/type", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "犯罪区域和犯罪类型", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getCrimeInfoStatisticsCrimeRegionMain(@RequestBody  ReportCrimeParamModel reportCrimeParamModel) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (reportCrimeParamModel != null) {
            reportRtnModels = crimeInfoStatisticsAnalysisService.getCrimeInfoStatisticsCrimeRegionMain(reportCrimeParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        }
        return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
    }

    /**
     * 犯罪类型和犯罪时间
     * @param reportCrimeParamModel
     * @return
     */
    @RequestMapping(value = "/statistics/crime/type/time", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "犯罪类型和犯罪时间", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getCrimeInfoStatisticsCrimeTypeMain(@RequestBody  ReportCrimeParamModel reportCrimeParamModel){
    List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (reportCrimeParamModel != null) {
            reportRtnModels = crimeInfoStatisticsAnalysisService.getCrimeInfoStatisticsCrimeTypeMain(reportCrimeParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        }
        return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
    }

    /**
     * 犯罪时间和犯罪区域
     * @param reportCrimeParamModel
     * @return
     */
    @RequestMapping(value = "/statistics/crime/region/time", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "犯罪时间和犯罪区域", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getCrimeInfoStatisticsCrimeTimeMain(@RequestBody  ReportCrimeParamModel reportCrimeParamModel){
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (reportCrimeParamModel != null) {
            reportRtnModels = crimeInfoStatisticsAnalysisService.getCrimeInfoStatisticsCrimeTimeMain(reportCrimeParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        }
        return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
    }


}
