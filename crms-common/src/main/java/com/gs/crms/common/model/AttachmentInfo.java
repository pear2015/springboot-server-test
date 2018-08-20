package com.gs.crms.common.model;

/**
 * Created by tanjie on 2017/7/28.
 */
public class AttachmentInfo {
    private String attachmentId;
    private String fileFormatType;
    private String fileName;
    private String attachmenttypeId;
    private String filePath;

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
}
