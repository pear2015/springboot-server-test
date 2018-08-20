package com.gs.crms.webapi.controller;

import com.gs.crms.applycertify.contract.model.WaitApportion;
import com.gs.crms.applycertify.contract.service.ApportionUserService;
import com.gs.crms.common.annotation.LimitIPRequestAnnotation;
import com.gs.crms.common.configs.HttpError;

import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangqiang on 2017/9/28.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "分派服务使用 Api")
public class ApportionUserContrller {

    Logger s = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApportionUserService apportionUserService;

    /**
     * 获取没有新分析任务的分析员ID
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/analysis/post", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取没有新分析任务的分析员ID", notes = "分派服务使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<String>> findNoAnalysisTaskAnalystList(@RequestBody List<String> ids) {
        List<String> result = new ArrayList<>();
        if (!ids.isEmpty()) {
            return new ResponseEntity<>(apportionUserService.findNoAnalysisTaskAnalystList(ids), HttpStatus.OK);
        }
      return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }


    /**
     * 获取待分派的任务队列
     *
     * @return
     */
    @RequestMapping(value = "/waitApportion/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取待分派的任务队列", notes = "waitApportion controller")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<WaitApportion>> getAllWaitApportion(@RequestParam(required = true) String waitReasonType) {
        if (StringUtils.isBlank(waitReasonType)) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(apportionUserService.getAllWaitApportionByWaitReasonType(waitReasonType), HttpStatus.OK);
    }


    /**
     * 删除待分派数据通过id 集合
     *
     * @return
     */
    @RequestMapping(value = "/waitApportion/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "删除待分派数据通过id 集合", notes = "waitApportion controller")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<Boolean> deleteApportion(@ApiParam(value = "已激活的任务ID", required = true) @RequestBody List<String> ids) {

        boolean result = apportionUserService.deleteApportion(ids);
        if (result) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
