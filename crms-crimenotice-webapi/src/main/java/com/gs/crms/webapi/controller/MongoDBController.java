package com.gs.crms.webapi.controller;

import com.gs.crms.common.annotation.LimitIPRequestAnnotation;
import com.gs.crms.common.configs.HttpError;

import com.gs.crms.crimenotice.contract.model.monogdb.MongoFile;
import com.gs.crms.crimenotice.contract.service.MongoService;
import io.swagger.annotations.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangqiang on 2017/8/16.
 * MongoDB操作文件API
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "MongoDB文件数据库 Api")
public class MongoDBController {

    @Autowired
    private MongoService mongoService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 保存单个文件
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/crms/file/store", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "保存单个文件", notes = "MongoDB store file")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<String> storeSingleFile(@ApiParam(value = "文件", required = true) @RequestParam
                                                          MultipartFile file) {
        String fileId = "";
        try {
            MongoFile mongoFile = MongoFile.create(file);
            fileId = mongoService.storeFile(mongoFile);
        } catch (IOException e) {
            logger.error("Remote Server Exception:storeSingleFile", e);
            return new ResponseEntity<>(fileId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (fileId == null || "".equals(fileId)) {
            return new ResponseEntity<>(fileId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(fileId, HttpStatus.OK);
    }

    /**
     * 通过文件id获取文件
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/crms/file/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取单个文件", notes = "MongoDB get file")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<byte[]> getSingleFile(@ApiParam(value = "文件Id", required = true) @PathVariable
                                                        String id) {
        byte[] result = null;

        if (id == null || "".equals(id)) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        result = mongoService.find(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 获取多个文件
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/crms/file/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取多个文件", notes = "获取多个文件")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<byte[]>> getSingleFile(@ApiParam(value = "文件Id集合", required = true) @RequestBody List<String> ids) {
        List<byte[]> result = new ArrayList<>();

        if (!ids.isEmpty()) {
            for (String id : ids) {
                byte[] bytes = mongoService.find(id);
                if (bytes.length > 0) {
                    result.add(mongoService.find(id));
                }
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
