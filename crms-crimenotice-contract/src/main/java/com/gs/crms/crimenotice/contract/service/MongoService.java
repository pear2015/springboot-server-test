package com.gs.crms.crimenotice.contract.service;

import com.gs.crms.crimenotice.contract.model.monogdb.MongoFile;

/**
 * Created by zhangqiang on 2017/8/16.
 * mongodb操作附件接口
 */
public interface MongoService {
    /**
     * 保存附件
     * @param file
     * @return
     */
    String storeFile(MongoFile file) ;

    /**
     * 通过_id查找单个文件
     * @param id
     * @return
     */
    byte[] find(String id);
}
