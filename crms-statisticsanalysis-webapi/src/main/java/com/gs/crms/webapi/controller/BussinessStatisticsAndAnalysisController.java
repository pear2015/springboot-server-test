package com.gs.crms.webapi.controller;

import com.gs.crms.common.annotation.LimitIPRequestAnnotation;
import com.gs.crms.common.configs.HttpError;
import com.gs.crms.statisticsanalysis.contract.model.ReportParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;
import com.gs.crms.statisticsanalysis.contract.service.BussinessDealStatisticsanalysisService;
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
@Api(value = "/api/v1", description = "业务办理统计分析 Api")
public class BussinessStatisticsAndAnalysisController {
    @Autowired
    private BussinessDealStatisticsanalysisService bussinessDealStatisticsanalysisService;
    /**
     * 通过时间获取业务处理统计(按年，月份)
     * @param reportParamModel
     * @return
     */
    @RequestMapping(value = "/statistics/business/get/dateType", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "按年/月/自定义时间获取业务办理统计数量", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getBussDealStatisticsByDateType(@RequestBody ReportParamModel reportParamModel){
        List<ReportRtnModel> reportRtnModels = null;
        if (reportParamModel != null){
            reportRtnModels = bussinessDealStatisticsanalysisService.getBussDealStatisticsByDate(reportParamModel);
            return new ResponseEntity<>(reportRtnModels,HttpStatus.OK);
        }
        return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
    }

    /**
     * 业务处理统计
     * 按照申请类型和采集点统计
     * @param reportParamModel
     * @return
     */
    @RequestMapping(value = "/statistics/business/get/type/centerCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "按照申请类型+采集点统计", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getBussDealStatisticsByApplyType(@RequestBody ReportParamModel reportParamModel){
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (reportParamModel != null&& StringUtils.isNotEmpty(reportParamModel.getDateType())) {
            reportRtnModels = bussinessDealStatisticsanalysisService.getBussDealStatisticsByApplyTypeAndCenterCode(reportParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 业务处理统计
     * 按照申请类型+ 申请目的 统计
     * @param reportParamModel
     * @return
     */
    @RequestMapping(value = "/statistics/business/get/type/purpose", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据 申请类型+ 申请目的统计", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getBussDealStatisticsByApplyPurpose(@RequestBody ReportParamModel reportParamModel){
        List<ReportRtnModel> reportRtnModels = null;
        if (reportParamModel != null){
            reportRtnModels = bussinessDealStatisticsanalysisService.getBussDealStatisticsByTypeAndPurpose(reportParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        }else
            return new ResponseEntity<>(reportRtnModels,HttpStatus.BAD_REQUEST);
    }

    /**
     * 业务处理统计
     * 按照 所在地（采集点）+申请目的
     * @param reportParamModel
     * @return 申请结果
     */
    @RequestMapping(value = "/statistics/business/get/centerCode/purpose", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "按照 所在地（采集点）+申请目的 统计", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getBussDealStatisticsByCenterCodeAndPurpose(@RequestBody ReportParamModel reportParamModel) {
        List<ReportRtnModel> reportRtnModels = null;
        if (reportParamModel != null) {
            reportRtnModels = bussinessDealStatisticsanalysisService.getBussDealStatisticsByCenterCodeAndPurpose(reportParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * 业务分析 平均时长和办理结果分析3
     * 站点下的平均办理时长
     *
     * @param reportParamModel
     * @return 申请结果
     */
    @RequestMapping(value = "/statistics/business/get/result/avg", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "站点下的平均办理时长+办理结果", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getBussDealStatisticsByAnalysisResultId(@RequestBody ReportParamModel reportParamModel) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (reportParamModel != null) {
            reportRtnModels = bussinessDealStatisticsanalysisService.getBussDealStatisticsAvgByCenterId(reportParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * 业务分析 平均时长和办理结果分析3
     * 站点下的平均办理时长
     *
     * @param reportParamModel
     * @return 申请结果
     */
    @RequestMapping(value = "/statistics/business/get/audit/avg", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "站点下的平均办理时长+审核结果", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getBussDealStatisticsByAnalysisAuditId(@RequestBody ReportParamModel reportParamModel) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (reportParamModel != null) {
            reportRtnModels = bussinessDealStatisticsanalysisService.getBussDealStatisticsByAnalysisAuditId(reportParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
        }
    }
}
