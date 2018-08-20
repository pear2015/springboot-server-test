package com.gs.crms.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenwei on 2017/8/22.
 */
@ApiModel(value = "错误模型")
public class Error {
    @ApiModelProperty(value = "自定义错误信息")
    private final String message;
    @ApiModelProperty(value = "自定义错误码")
    private final int code;


    /**
     * Instantiates a new Error.
     *
     * @param errorCode the error code
     * @param message   the message
     */
    public Error(int errorCode, String message) {
        this.message = message;
        this.code = errorCode;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

}