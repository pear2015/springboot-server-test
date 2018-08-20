package com.gs.crms.common.enums;


import javax.persistence.*;
import java.util.Date;

/**
 * 附件表
 * Created by tanjie on 2017/7/10.
 */
@Entity
@Table(name = "b_attachment", schema = "crms_crime")
public class AttachmentEntity {
    /*
    主键
     */
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String attachmentId;
    /*
        附件扩展名
         */
    @Column(name = "file_format_type", length = 36,nullable = false)
    private String fileFormatType;
    /*
    附件文件名
     */
    @Column(name = "file_name", length = 128,nullable = false)
    private String fileName;
    /*
    外键附件类型
    */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_type_id", insertable = false, updatable = false)
    private AttachmentTypeEntity attachmentTypeEntity;
    /*
    附件类型
     */
    @Column(name = "attachment_type_id", length = 36)
    private String attachmenttypeId;

    /*
    附件路径(存储在mongDB数据库具体的文件)
     */
    @Column(name = "file_path", length = 256)
    private String filePath;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getFileFormatType() {
        return fileFormatType;
    }

    public void setFileFormatType(String fileFormatType) {
        this.fileFormatType = fileFormatType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public AttachmentTypeEntity getAttachmentTypeEntity() {
        return attachmentTypeEntity;
    }

    public void setAttachmentTypeEntity(AttachmentTypeEntity attachmentTypeEntity) {
        this.attachmentTypeEntity = attachmentTypeEntity;
    }

    public String getAttachmenttypeId() {
        return attachmenttypeId;
    }

    public void setAttachmenttypeId(String attachmenttypeId) {
        this.attachmenttypeId = attachmenttypeId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
