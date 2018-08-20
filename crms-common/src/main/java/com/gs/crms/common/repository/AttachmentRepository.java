package com.gs.crms.common.repository;

import com.gs.crms.common.enums.AttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 公告信息附件Repository
 * Created by tanjie on 2017/7/10.
 */
@Repository
public interface AttachmentRepository extends JpaRepository<AttachmentEntity,String> {
    /**
     *  通过id获取到附件对象
     * @param attachmentId
     * @return
     */
    AttachmentEntity getByAttachmentId(String attachmentId);

    @Query(value = "select a from AttachmentEntity a where attachmentId in :attachmentIds")
    List<AttachmentEntity> getAllByAttachmentId(@Param("attachmentIds") List<String> attachmentIds);
    /**
    * 排序
     */
    @Query(value = "select a from AttachmentEntity a where a.attachmentId in :attachmentIds order by  a.createTime ASC ")
    List<AttachmentEntity> findAllOrderByCreateTimeAsc(@Param("attachmentIds") List<String> attachmentIds);
}
