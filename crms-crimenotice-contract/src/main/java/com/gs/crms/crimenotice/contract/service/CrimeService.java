package com.gs.crms.crimenotice.contract.service;

import com.gs.crms.common.model.AttachmentInfo;
import com.gs.crms.common.model.ReturnBase;
import com.gs.crms.crimenotice.contract.model.*;
import com.gs.crms.crimenotice.contract.model.search.*;

import java.util.List;
import java.util.Map;

/**
 * Created by tanjie on 2017/7/25.\
 * 犯罪基本信息和人员信息相关接口
 */
public interface CrimeService {

    /**
     * 1：保存犯罪基本信息
     * 2: 保存犯罪人员基本信息
     * 3：保存公告信息
     * 4: 保存公告关联附件信息
     *
     * @return boolean
     */
    Map<String,Object> addCrimeAndNoticeInfo(CrimeAndNoticeExtendInfo crimeAndNoticeExtendInfo);

    /**
     * 1：更新犯罪基本信息
     * 2: 更新犯罪人员基本信息
     * 3：更新公告信息
     * 4: 更新公告关联附件信息
     *
     * @return boolean
     */
    Map<String,Object> updateCrimeAndNoticeInfo(CrimeAndNoticeExtendInfo crimeAndNoticeExtendInfo);

    /**
     * 根据公告ID列表获取公告详情
     *
     * @param noticeIds
     * @return
     */
    List<CrimeAndNoticeExtendInfo> getNoticeDetailByNoticeIds(List<String> noticeIds);

    /**
     * 根据公告Id列表获取公告列表分页
     *
     * @param ids
     * @return
     */
    ReturnBase<List<CrimeAndNoticeExtendInfo>> getNoticeByNoticeIdList(List<String> ids, int pages, int pageSize);

    /**
     * 公告编号查重
     *
     * @param noticeNumber
     * @return
     */
    boolean getNoticeNumberUseStatus(String noticeNumber, String noticeId);


    /**
     * 根据公告Id 获取公告详情
     *
     * @param noticeId
     * @return
     */
    CrimeAndNoticeExtendInfo findCrimeAndNoticeByNoticeId(String noticeId);

    /**
     * 根据犯罪人id获取公告详情
     *
     * @param crimePersonId
     * @return
     */
    CrimeAndNoticeListInfo getNoticeListByCrimePersonId(String crimePersonId);

    /**
     * 提交审核
     *
     * @param noticeInfo
     * @return
     */
    Map<String,Object> submitAudit(NoticeInfo noticeInfo);


    /**
     * 工作流分派后数据保存
     *
     * @param noticeApportion
     * @return
     */
    boolean saveNoticeApportion(NoticeApportion noticeApportion);

    /**
     * 公告编辑优先级
     *
     * @param noticeInfo
     * @return
     */
    boolean editPriority(NoticeInfo noticeInfo);

    /**
     * 根据ID获取公告
     *
     * @param id
     * @return
     */
    NoticeInfo getNoticeById(String id);

    /**
     * 根据公告id获取犯罪审核列表
     * @param noticeId
     * @return
     */
    CrimeAndNoticeExtendInfo findAuditDetailByNoticeId(String noticeId);

    /**
     * 获取附件信息
     */

     List<AttachmentInfo> findAttachmentByIds(String ids);
}
