package com.gs.crms.webapi.controller;

import com.gs.crms.common.annotation.LimitIPRequestAnnotation;
import com.gs.crms.common.configs.HttpError;
import com.gs.crms.common.model.ReturnBase;
import com.gs.crms.common.utils.JavaBeanValidationUtil;
import com.gs.crms.crimenotice.contract.model.CrimeInfo;
import com.gs.crms.crimenotice.contract.model.CrimeIntegrated.CrimePersonQuery;
import com.gs.crms.crimenotice.contract.model.CrimePersonInfo;
import com.gs.crms.crimenotice.contract.model.NoticeInfo;
import com.gs.crms.crimenotice.contract.model.search.CrimePersonSearchInfo;
import com.gs.crms.crimenotice.contract.model.search.CrimeSearchInfo;
import com.gs.crms.crimenotice.contract.service.CrimeIntegratedService;
import com.gs.crms.crimenotice.contract.service.CrimeSearchService;
import com.gs.crms.crimenotice.contract.service.CrimeService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 犯罪公告和犯罪信息查询 REST API
 * Created by  on 2017/3/2.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "犯罪公告和犯罪信息查询相关 Api")
public class CrimeSearchController {
    @Autowired
    private CrimeService crimeService;
    @Autowired
    private CrimeSearchService crimeSearchService;

    @Autowired
    private CrimeIntegratedService crimeIntegratedService;
    //region 1 内部查询 ---公告编号
    /**
     * 根据公告编号查重
     *
     * @param noticeNumber 公告编码
     * @return
     */
    @RequestMapping(value = "/crms/noticeNumber/hasUse", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据公告编号查重", notes = "业务系统使用 使用角色(归档员)")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<Boolean> getCrimeTypeListInfo(@RequestParam(required = true) String noticeNumber, @RequestParam(required = false) String noticeId) {
        if (StringUtils.isEmpty(noticeNumber)) {
            return new ResponseEntity<>(true, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(crimeService.getNoticeNumberUseStatus(noticeNumber, noticeId), HttpStatus.OK);
    }
    //endregion
    //region 2 内部查询 ---犯罪人查询  回填
    /**
     * 数据来源：
     * 通过证件类型、证件号码和姓名模糊匹配获取申请人列表
     *
     * @return
     */
    @RequestMapping(value = "/crms/crimePerson/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "匹配犯罪人员信息进行合并", notes = "业务系统使用 使用角色(归档员)")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<ReturnBase<List<CrimePersonInfo>>> getApplyPersonInfo(@RequestBody CrimePersonSearchInfo crimePersonSearchInfo, @RequestParam(required = false) String backFillCriminalId) {
        ReturnBase<List<CrimePersonInfo>> result = new ReturnBase<>();
        if(crimePersonSearchInfo!=null&& JavaBeanValidationUtil.validate(crimePersonSearchInfo)){
            result= crimeSearchService.getCrimePersonInfoBySearch(crimePersonSearchInfo, backFillCriminalId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
    /**
     * 数据来源：
     * 通过证件类型、证件号码和姓名模糊匹配获取申请人列表
     *
     * @return
     */
    @RequestMapping(value = "/crms/crimePerson/findByCertNumberOrName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据罪人信息回填-根据条件查询人口库", notes = "业务系统使用 使用角色(归档员)")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<CrimePersonInfo>> getApplyPersonInfoByQuery(@RequestBody CrimePersonSearchInfo crimePersonSearchInfo) {
        List<CrimePersonInfo> result = new ArrayList<>();
        if(crimePersonSearchInfo!=null&& JavaBeanValidationUtil.validate(crimePersonSearchInfo)&&crimePersonSearchInfo.validateData()){
            result= crimeSearchService.getCrimePersonInfoByCertNumberOrName(crimePersonSearchInfo);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
    /**
     * 根据犯罪人id获取犯罪信息
     *
     * @return
     */
    @RequestMapping(value = "/crms/crimePerson/{crimePersonId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据犯罪人id获取犯罪人信息", notes = "业务系统使用 使用角色(归档员)")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<CrimePersonInfo> getCriminalByCrimePersonId(@PathVariable (required = true)  String crimePersonId) {
        CrimePersonInfo result = new CrimePersonInfo();
        if(StringUtils.isBlank(crimePersonId)){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(crimeSearchService.getCriminalByCrimePersonId(crimePersonId), HttpStatus.OK);
    }
   //endregion
    //region 3 内部查询 ---犯罪信息查询
    /**
     * 犯罪信息查询
     * 根据人员基本信息,带分页
     *
     * @return
     */
    @RequestMapping(value = "/crms/inquire/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "犯罪查询列表", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<ReturnBase<List<CrimePersonInfo>>>findAllCrimePersonInfoBySearch(@ApiParam(value = "查询条件:人员基本信息") @RequestBody CrimePersonQuery crimePersonQuery) {
        ReturnBase<List<CrimePersonInfo>> result = new ReturnBase<>();
        if(crimePersonQuery!=null&&JavaBeanValidationUtil.validate(crimePersonQuery)){
            result=crimeIntegratedService.findAllCrimePersonInfoBySearch(crimePersonQuery);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
    //endregion
    //region 4 内部查询 ---公告查询
    /**
     * 公告查询历史记录
     *
     * @param crimeSearchInfo
     * @return
     */
    @RequestMapping(value = "/crms/notice/history", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "公告历史记录查询(已通过 已拒绝 已失效)", notes = "业务系统使用 使用角色(归档员、审核员)")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<ReturnBase<List<NoticeInfo>>>findNoticeListBySearch(@RequestBody CrimeSearchInfo crimeSearchInfo) {
        ReturnBase<List<NoticeInfo>> result=new ReturnBase<>();
        if(crimeSearchInfo!=null&&JavaBeanValidationUtil.validate(crimeSearchInfo)){
            result=  crimeSearchService.findNoticeListByHistory(crimeSearchInfo);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
    /**
     * 查询归档员的重新分析列表
     *
     * @param pages
     * @param pageSize
     * @param filterId
     * @return
     */
    @RequestMapping(value = "/notice/reply/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询归档员的重新分析列表", notes = "业务系统使用 使用角色(归档员)")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<ReturnBase<List<NoticeInfo>>> getAnalystNoticeListById(@ApiParam(value = "页码") @RequestParam int pages, @ApiParam(value = "每页数量") @RequestParam int pageSize, @ApiParam(value = "审核员Id") @RequestParam String filterId, @ApiParam(value = "业务类型") @RequestParam String businessType) {
        ReturnBase<List<NoticeInfo>> returnBase = new ReturnBase<>();
        if (StringUtils.isBlank(filterId) || StringUtils.isBlank(businessType)) {
            return new ResponseEntity<>(returnBase, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(crimeSearchService.getAnalystTaskList(pages, pageSize, filterId, businessType), HttpStatus.OK);
    }

    /**
     * 查询归档员审核中的公告
     *
     * @param crimeSearchInfo
     * @return
     */
    @RequestMapping(value = "/crms/notice/waitAudit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询归档员审核中的公告", notes = "业务系统使用 使用角色(归档员)")

    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<ReturnBase<List<NoticeInfo>>> findNoticeListByWaitAudit(@RequestBody CrimeSearchInfo crimeSearchInfo) {
        ReturnBase<List<NoticeInfo>> result = new ReturnBase<>();
        if(crimeSearchInfo!=null&& JavaBeanValidationUtil.validate(crimeSearchInfo)&&StringUtils.isNotEmpty(crimeSearchInfo.getEnterPersonId())){
            result=   crimeSearchService.findNoticeListByWaitAudit(crimeSearchInfo);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    /**
     * 公告查询 查询可编辑优先级的公告列表
     *
     * @param crimeSearchInfo
     * @return
     */
    @RequestMapping(value = "/crms/notice/priority", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "公告查询可编辑优先级的列表", notes = "业务系统使用 使用角色(归档员、审核员)")

    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<ReturnBase<List<NoticeInfo>>> findNoticeListByPrioritySearch(@RequestBody CrimeSearchInfo crimeSearchInfo) {
        ReturnBase<List<NoticeInfo>> result = new ReturnBase<>();
        if(crimeSearchInfo!=null&&JavaBeanValidationUtil.validate(crimeSearchInfo)){
            result= crimeSearchService.findNoticeListByPriority(crimeSearchInfo);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
    /**
     * 查询审核员的待审核列表
     *
     * @param pages
     * @param pageSize
     * @param auditorId
     * @return
     */
    @RequestMapping(value = "/notice/audit/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询审核员的待审核列表", notes = "业务系统使用 使用角色(审核员)")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<ReturnBase<List<NoticeInfo>>> getAuditNoticeListById(@ApiParam(value = "页码") @RequestParam int pages, @ApiParam(value = "每页数量") @RequestParam int pageSize, @ApiParam(value = "审核员Id") @RequestParam String auditorId, @ApiParam(value = "业务类型") @RequestParam String businessType) {
        ReturnBase<List<NoticeInfo>> result = new ReturnBase<>();
        if (StringUtils.isBlank(auditorId) || StringUtils.isBlank(businessType)) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(crimeSearchService.getAuditorTaskList(pages, pageSize, auditorId, businessType), HttpStatus.OK);
    }

    /**
     * 查询审核员已审核的公告列表
     *
     * @param crimeSearchInfo
     * @return
     */
    @RequestMapping(value = "/notice/hasAudit/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询审核员的已审核列表", notes = "业务系统使用 使用角色(审核员)")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public  ResponseEntity<ReturnBase<List<NoticeInfo>>> getHasAuditNoticeListById(@RequestBody CrimeSearchInfo crimeSearchInfo) {
        ReturnBase<List<NoticeInfo>> result = new ReturnBase<>();
        if (crimeSearchInfo == null || crimeSearchInfo.getAuditPersonId() == null) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(crimeSearchService.getHasAuditorTaskList(crimeSearchInfo), HttpStatus.OK);
    }
    //endregion

    //region 5 外部查询
     /**
     * 通过身份证号查询犯罪人信息
     * @return
     */
    @RequestMapping(value = "/crms/crimePerson/byCerNumber", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "通过身份证号查询犯罪人信息", notes = "外部业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public  ResponseEntity<List<CrimePersonInfo>> getApplyPersonInfoByCerNumber(@RequestParam (required = true) String  certificateNumber) {
        List<CrimePersonInfo> result = new ArrayList<>();
        if(StringUtils.isEmpty(certificateNumber)){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        result= crimeSearchService.getApplyPersonInfoByCerNumber(certificateNumber);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    /**
     * 通过身份证号查询犯罪信息列表
     * @return
     */
    @RequestMapping(value = "/crms/crimeInfo/getListByCerNumber", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "通过身份证号查询犯罪信息列表", notes = "业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public  ResponseEntity<List<CrimeInfo>> getApplyPersonInfoListByCerNumber(@RequestParam (required = true) String  certificateNumber) {
        List<CrimeInfo> result = new ArrayList<>();
        if(StringUtils.isEmpty(certificateNumber)){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        result= crimeSearchService.getCrimeInfoByCerNumber(certificateNumber);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    /**
     * 根据犯罪信息Id查询犯罪信息详情
     */
    @RequestMapping(value = "/crms/crimeInfo/{crimeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据犯罪信息Id查询犯罪信息详情", notes = "外部业务系统使用")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public  ResponseEntity<CrimeInfo> getApplyPersonDetail(@PathVariable (required = true) String  crimeId) {
        if(StringUtils.isEmpty(crimeId)){
            return new ResponseEntity<>(new CrimeInfo(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(crimeSearchService.getCrimeInfoDetailByCrimeId(crimeId), HttpStatus.OK);
    }
    //endregion
}
