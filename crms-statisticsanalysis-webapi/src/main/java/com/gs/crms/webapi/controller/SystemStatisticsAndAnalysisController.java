package com.gs.crms.webapi.controller;

import com.gs.crms.common.annotation.LimitIPRequestAnnotation;
import com.gs.crms.common.configs.HttpError;
import com.gs.crms.statisticsanalysis.contract.model.ReportParamModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;
import com.gs.crms.statisticsanalysis.contract.service.SysUsageStatisticsanalysisService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengyali on 2017/11/20.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "系统使用情况统计分析 Api")
public class SystemStatisticsAndAnalysisController {
    @Autowired
    private SysUsageStatisticsanalysisService sysUsageStatisticsanalysisService;

    /**
     * 系统使用情况统计分析
     * @param reportParamModel
     * @return
     */
    @RequestMapping(value = "/statistics/sysUsage/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "按年/月/自定义时间获取获取某个站点系统使用情况", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getSysUsageStatisticByDate(@RequestBody ReportParamModel reportParamModel) {
        List<ReportRtnModel> reportRtnModels = new ArrayList<>();
        if (reportParamModel != null) {
            reportRtnModels = sysUsageStatisticsanalysisService.getSysUsageStatisticByDate(reportParamModel);
            return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
        }
        return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
    }
}
