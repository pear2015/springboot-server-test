package com.gs.crms.crimenotice.service.repository.extend;

import com.gs.crms.common.enums.AttachmentEntity;

import java.util.List;

/**
 * Created by tanjie on 2017/7/28.
 */
@FunctionalInterface
public interface CrimeRepositoryExtend {
    /**
     * Gets attachment list info.
     *
     * @param attachmentIdList the attachment id list
     * @return the attachment list info
     */
    List<AttachmentEntity> getAttachmentListInfo(String attachmentIdList);
}
