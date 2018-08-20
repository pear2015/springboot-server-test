package com.gs.crms.crimenotice.contract.model.monogdb;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangqiang on 2017/8/16.
 * 保存至MongoDB时描述文件的数据
 */
public class MongoFile {
    /**
     * 文件名name
     */
    private String name;
    /**
     * 文件类型mimeType
     * 如：image/png,image/jpg...
     */
    private String mimeType;
    /**
     * 描述文件的元数据metadata
     */
    private Map<String,Object> metaData;
    /**
     * 文件内容byte[]
     */
    private byte[] content;

    /**
     * 创建一个MongoFile文件传输对象
     *
     * @param file the file
     * @return mongo file
     * @throws IOException the io exception
     */
    public static MongoFile create(MultipartFile file) throws IOException {
        MongoFile o = new MongoFile();
        o.metaData = new HashMap<>();
        o.content = file.getBytes();
        o.mimeType = file.getContentType();
        return o;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets mime type.
     *
     * @return the mime type
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Sets mime type.
     *
     * @param mimeType the mime type
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * Gets meta data.
     *
     * @return the meta data
     */
    public Map<String, Object> getMetaData() {
        return metaData;
    }

    /**
     * Sets meta data.
     *
     * @param metaData the meta data
     */
    public void setMetaData(Map<String, Object> metaData) {
        this.metaData = metaData;
    }

    /**
     * Get content byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(byte[] content) {
        this.content = content;
    }
}
