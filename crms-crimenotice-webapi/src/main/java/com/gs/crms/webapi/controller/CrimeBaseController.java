package com.gs.crms.webapi.controller;

import com.gs.crms.common.annotation.LimitIPRequestAnnotation;
import com.gs.crms.common.configs.HttpError;
import com.gs.crms.crimenotice.contract.model.CourtInfo;
import com.gs.crms.crimenotice.contract.model.CrimeTypeInfo;
import com.gs.crms.crimenotice.contract.service.CrmsBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhengyali on 2017/10/30.
 * 基础数据Api
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "犯罪公告基础数据相关 Api")
public class CrimeBaseController {
    @Autowired
    private CrmsBaseService crmsBaseService;
    /***
     * 查询犯罪类型列表
     * * @return
     */
    @RequestMapping(value = "/crms/crimeType/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取犯罪类型列表", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<CrimeTypeInfo>> getCrimeTypeListInfo() {
        List<CrimeTypeInfo> result = crmsBaseService.getCrimeTypeInfoList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 获取法院信息
     *
     * @return
     */
    @RequestMapping(value = "/crms/court/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取法院信息", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<CourtInfo>> getCourtListInfo() {
        List<CourtInfo> result = crmsBaseService.getCourtList();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
