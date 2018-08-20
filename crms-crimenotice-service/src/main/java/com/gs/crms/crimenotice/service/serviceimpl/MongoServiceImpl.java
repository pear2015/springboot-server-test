package com.gs.crms.crimenotice.service.serviceimpl;

import com.gs.crms.crimenotice.contract.model.monogdb.MongoFile;
import com.gs.crms.crimenotice.contract.service.MongoService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangqiang on 2017/8/16.
 * MongoDB操作文件相关实现
 */
@Service
public class MongoServiceImpl implements MongoService {

    @Autowired
    @Qualifier("gridFsTemplate")
    protected GridFsOperations gridFsOperations;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 保存文件
     * @param file
     * @return
     */
    @Override
    public String storeFile(MongoFile file) {
        InputStream is = null;
        GridFSFile gridFSFile = null;
        try {
            is = new ByteArrayInputStream(file.getContent());
            DBObject metadata = getDBObject(file);
            //是否加密
            metadata.put("encrypted", false);
            //保存时间
            metadata.put("storeTime", new Date());
            gridFSFile = gridFsOperations.store(is, file.getName(), file.getMimeType(), metadata);

        } catch (Exception e) {
            logger.error("StoreFile Exception:" + e);
        }
        String fileId = null;
        if (gridFSFile != null) {
            fileId = gridFSFile.get("_id").toString();
        }
        return fileId;
    }

    /**
     * 通过文件ID获取二进制数组
     * @param id
     * @return
     */
    @Override
    public byte[] find(String id) {
        List<GridFSDBFile> list = gridFsOperations.find(
                new Query().addCriteria(Criteria.where("_id").is(id)));
        byte[] result = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GridFSDBFile file = list.get(0);
            file.writeTo(byteArrayOutputStream);
            result = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            logger.error("find error:" + e);
        }
        return result;
    }

    /**
     * 将手动设置的metadata转换为DBObject
     *
     * @param file
     * @return
     */
    private DBObject getDBObject(MongoFile file) {

        DBObject metadata = new BasicDBObject();
        if (file.getMetaData() != null) {
            for (Map.Entry<String, Object> entry : file.getMetaData().entrySet())
                metadata.put(entry.getKey(), entry.getValue());
        }
        return metadata;
    }

}
