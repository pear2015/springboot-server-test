package com.gs.crms.crimenotice.service.serviceimpl;

import com.gs.crms.crimenotice.contract.model.monogdb.MongoFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.web.multipart.MultipartFile;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by zhufengjie on 2017/9/8.
 */
public class MongoServiceImplTest {
    @Mock
    private  GridFsOperations gridFsOperations;
    @InjectMocks
    private MongoServiceImpl mongoService;

    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
    }
    @AfterMethod
    public void afterMethod(){
        reset(gridFsOperations);
    }

    /**
     * 保存文件
     * @throws Exception
     */
    @Test
    public void testStoreFile()throws Exception{
        GridFSFile gridFSFile = new GridFSFile() {
        };
        MultipartFile multipartFile=mock(MultipartFile.class);
        gridFSFile.put("_id",new Integer(2));
        MongoFile mongoFile = MongoFile.create(multipartFile);
        mongoFile.setContent(new byte[100]);
        Map<String,Object> map =new HashMap<>();
        map.put("1","2");
        mongoFile.setMetaData(map);
        //正常情况
        when(gridFsOperations.store(any(), any(), any(), any())).thenReturn(gridFSFile);
        String fileId = mongoService.storeFile(mongoFile);
        Assert.assertNotNull(fileId);
        //抛异常的情况
        when(gridFsOperations.store(any(),any(), any(), any())).thenThrow(new RuntimeException());
        String fileId1 = mongoService.storeFile(mongoFile);
        Assert.assertEquals(fileId1,null);
    }

    /**
     * 通过文件ID获取二进制数组
     * @throws Exception
     */
    @Test
    public void testFind()throws Exception{
        String id = "1";

        List<GridFSDBFile> list = new ArrayList<>();
        GridFSDBFile gridFSDBFile = new GridFSDBFile(){};
        list.add(gridFSDBFile);
        when(gridFsOperations.find(any())).thenReturn(list);
        byte[] result =mongoService.find(id);
        Assert.assertNotNull(result);
        //当list等于空
        when(gridFsOperations.find(any())).thenReturn(null);
        byte[] result1 =mongoService.find(id);
        Assert.assertEquals(result1,null);
    }
}