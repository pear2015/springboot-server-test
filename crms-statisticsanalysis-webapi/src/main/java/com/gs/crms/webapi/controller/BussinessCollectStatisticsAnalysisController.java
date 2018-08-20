package com.gs.crms.webapi.controller;

import com.gs.crms.common.annotation.LimitIPRequestAnnotation;
import com.gs.crms.common.configs.HttpError;
import com.gs.crms.statisticsanalysis.contract.model.ReportApplyModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportBussModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportReturnModel;
import com.gs.crms.statisticsanalysis.contract.model.ReportRtnModel;
import com.gs.crms.statisticsanalysis.contract.service.BussinessCollectStatisticsAnalysisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by chenwei on 2018/1/3.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "业务量(个人申请,政府申请，犯罪公告)汇总统计Api")
public class BussinessCollectStatisticsAnalysisController {
    @Autowired
    private BussinessCollectStatisticsAnalysisService bussinessCollectStatisticsAnalysis;
    private  Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 通过时间获取业务处理统计(按年)
     * @return
     */
    @RequestMapping(value = "/statistics/bussinesscollect/year", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "按年统计业务量", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<ReportBussModel> getBussCollectForYear(){
       try {
         return  new ResponseEntity<>(bussinessCollectStatisticsAnalysis.getBussForYear(), HttpStatus.OK);
       }catch (Exception ex){
           logger.error("BussinessCollectStatisticsAnalysisController->getBussCollectForYear have error:",ex);
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    /**
     * 通过时间获取业务处理统计(按月)
     * @return
     */
    @RequestMapping(value = "/statistics/bussinesscollect/month", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "按月统计业务量", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<ReportBussModel> getBussCollectForMonth(){
        try {
            return  new ResponseEntity<>(bussinessCollectStatisticsAnalysis.getBussFordMonth(), HttpStatus.OK);
        }catch (Exception ex){
            logger.error("BussinessCollectStatisticsAnalysisController->getBussCollectForMonth have error:",ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 通过时间获取业务处理统计(按周)
     * @return
     */
    @RequestMapping(value = "/statistics/bussinesscollect/week", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "按周统计业务量", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<ReportBussModel> getBussCollectForWeek(){
        try {
            return  new ResponseEntity<>(bussinessCollectStatisticsAnalysis.getBussFordWeek(), HttpStatus.OK);
        }catch (Exception ex){
            logger.error("BussinessCollectStatisticsAnalysisController->getBussCollectForWeek have error:",ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 通过时间获取业务处理统计(按日)
     * @return
     */
    @RequestMapping(value = "/statistics/bussinesscollect/day", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "按日统计业务量", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<ReportBussModel> getBussCollectForDay(){
        try {
            return  new ResponseEntity<>(bussinessCollectStatisticsAnalysis.getBussForDay(), HttpStatus.OK);
        }catch (Exception ex){
            logger.error("BussinessCollectStatisticsAnalysisController->getBussCollectForDay have error:",ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 本周、本月、本年的公告数据统计
     * @return
     */
    @RequestMapping(value = "/statistics/bussinesscollect/notice", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "本周、本月、本年的公告数据统计", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<ReportReturnModel> getBussCollectForNotice(){
        try {
            return  new ResponseEntity<>(bussinessCollectStatisticsAnalysis.getBussFoNotice(), HttpStatus.OK);
        }catch (Exception ex){
            logger.error("BussinessCollectStatisticsAnalysisController->getBussCollectForNotice have error:",ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 今日、今年的申请数据统计
     * @return
     */
    @RequestMapping(value = "/statistics/bussinesscollect/apply", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "今日、今年的申请数据统计", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<ReportReturnModel> getBussCollectForApply(){
        try {
            return  new ResponseEntity<>(bussinessCollectStatisticsAnalysis.getBussForApply(), HttpStatus.OK);
        }catch (Exception ex){
            logger.error("BussinessCollectStatisticsAnalysisController->getBussCollectForApply have error:",ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 最近10天申请数据统计
     * @return
     */
    @RequestMapping(value = "/statistics/bussinesscollect/personApply/recent" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "最近10天个人申请数据统计", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getBussCollectForRecentApply(){
        try {
            return  new ResponseEntity<>(bussinessCollectStatisticsAnalysis.getBussForRecentApply(), HttpStatus.OK);
        }catch (Exception ex){
            logger.error("BussinessCollectStatisticsAnalysisController->getBussCollectForRecentApply have error:",ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 本周的申请业务量
     * @return
     */
    @RequestMapping(value = "/statistics/bussinesscollect/apply/week" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "本周的申请业务量", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getBussCollectForApplyWeek(){
        try {
            return  new ResponseEntity<>(bussinessCollectStatisticsAnalysis.getBussCollectForApplyWeek(), HttpStatus.OK);
        }catch (Exception ex){
            logger.error("BussinessCollectStatisticsAnalysisController->getBussCollectForApplyWeek have error:",ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 本月的申请业务量
     * @return
     */
    @RequestMapping(value = "/statistics/bussinesscollect/apply/month" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "本月的申请业务量", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getBussCollectForApplyMonth(){
        try {
            return  new ResponseEntity<>(bussinessCollectStatisticsAnalysis.getBussCollectForApplyMonth(), HttpStatus.OK);
        }catch (Exception ex){
            logger.error("BussinessCollectStatisticsAnalysisController->getBussCollectForApplyMonth have error:",ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 本年的申请业务量
     * @return
     */
    @RequestMapping(value = "/statistics/bussinesscollect/apply/year" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "本年的申请业务量", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<List<ReportRtnModel>> getBussCollectForApplyYear(){
        try {
            return  new ResponseEntity<>(bussinessCollectStatisticsAnalysis.getBussCollectForApplyYear(), HttpStatus.OK);
        }catch (Exception ex){
            logger.error("BussinessCollectStatisticsAnalysisController->getBussCollectForApplyYear have error:",ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 获取未完成业务列表的 未分析的(已提交+待分析) 审核中 待打印
     * @return
     */
    @RequestMapping(value = "/statistics/bussinesscollect/apply/{enteringPersonId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取未完成业务列表的-未分析 审核中 待打印", notes = "统计分析业务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 500, timeSecond = 1000)
    public ResponseEntity<ReportApplyModel> getBussDealStatisticsByAnalysisAuditId(@PathVariable (required = true)  String  enteringPersonId) {
        if(StringUtils.isBlank(enteringPersonId)){
            return new ResponseEntity<>(new ReportApplyModel(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bussinessCollectStatisticsAnalysis.getBussDealStatistics(enteringPersonId), HttpStatus.OK);
    }

}
