package com.gs.crms.common.enums;

import javax.persistence.*;

/**
 * 附件类型
 * Created by tanjie on 2017/7/10.
 */
@Entity
@Table(name = "d_attachment_type", schema = "crms_crime")
public class AttachmentTypeEntity {
    /*
    主键ID
     */
    @Id
    @Column(name = "id", nullable = false,length = 36)
    private String attachmenttypeId;

    /*
    名称(公告录入，个人申请，政府申请)
     */
    @Column(name = "attachment_name",length = 128,nullable = false)
    private String attachmentName;

    /*
    描述
     */
    @Column(name = "description",length = 2000)
    private  String description;

    public String getAttachmenttypeId() {
        return attachmenttypeId;
    }

    public void setAttachmenttypeId(String attachmenttypeId) {
        this.attachmenttypeId = attachmenttypeId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
