package com.gs.crms.webapi.controller;

/**
 * Created by zhengyali on 2017/10/31.
 */

import com.gs.crms.common.annotation.LimitIPRequestAnnotation;
import com.gs.crms.common.configs.HttpError;
import com.gs.crms.crimenotice.contract.model.NoticeApportion;
import com.gs.crms.crimenotice.contract.service.CrimeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 犯罪信息REST API
 * Created by tanjie on 2017/3/2.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "犯罪公告工作流相关 Api")
public class WorkFlowNoticeController {
    @Autowired
    private CrimeService crimeService;
    /**
     * 分派后保存对应的审核员
     */
    @RequestMapping(value = "/crms/notice/apportion/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "保存分派数据", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public boolean saveNoticeApportion(@ApiParam(value = "保存公告分派后的数据") @RequestBody NoticeApportion noticeApportion) {
        return crimeService.saveNoticeApportion(noticeApportion);
    }
}
