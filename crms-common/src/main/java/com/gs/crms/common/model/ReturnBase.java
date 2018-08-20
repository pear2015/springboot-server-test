package com.gs.crms.common.model;

import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

/**
 * Created by chenwei on 2017/8/22.
 *
 * @param <T> the type parameter
 */
public class ReturnBase<T> {
    @ApiModelProperty(value = "返回是否成功")
    private boolean success;
    @ApiModelProperty(value = "返回的数据")
    private T data;
    @ApiModelProperty(value = "错误信息")
    private Error error;
    @ApiModelProperty(value = "数据页(分页用)")
    private long totalPage;
    @ApiModelProperty(value = "数据量(分页用)")
    private long totalCount;
    private static Logger log = LoggerFactory.getLogger(ReturnBase.class);

    /**
     * 带参数有的返回结果
     *
     * @param <W> the type parameter
     * @param w   返回结果
     * @return 统一结果对象 return base
     */
    public static <W> ReturnBase<W> succeed(W w) {
        ReturnBase<W> returnBase = new ReturnBase<>();
        returnBase.setData(w);
        returnBase.setSuccess(true);
        return returnBase;
    }

    /**
     * 不带参数的返回结果
     *
     * @return 统一结果对象 return base
     */
    public static ReturnBase succeed() {
        ReturnBase returnBase = new ReturnBase<>();
        returnBase.setSuccess(true);
        returnBase.setData(true);
        return returnBase;
    }

    /**
     * 出错时的返回结果，只查找glink-common内的词条
     *
     * @param errorCode 错误码
     * @param args      参数
     * @return 统一结果对象 return base
     */
    public static ReturnBase error(int errorCode, Object... args) {
        Error error = new Error(errorCode, getErrorMessage(errorCode, args));
        return buildError(error);
    }

    /**
     * 出错时的返回结果，根据传入的MessageSource查找词条
     *
     * @param errorCode     错误码
     * @param messageSource message source
     * @param args          message参数
     * @return the return base
     */
    public static ReturnBase error(int errorCode, MessageSource messageSource, Object... args) {
        Error error;
        if (messageSource == null) {
            error = new Error(errorCode, getErrorMessage(errorCode, args));
        } else {
            String key = "errorCode." + errorCode;
            String message = getKeyMessage(messageSource, key, args);
            error = new Error(errorCode, message);
        }
        return buildError(error);
    }

    /**
     * 出错时的返回结果.
     *
     * @param errorCode 错误码
     * @param message   message参数，即最终显示的词条(不会再额外格式化)
     * @return the return base
     */
    public static ReturnBase error(int errorCode, String message) {
        Error error = new Error(errorCode, message);
        return buildError(error);
    }


    private static ReturnBase buildError(Error error) {
        ReturnBase returnBase = new ReturnBase();
        returnBase.setError(error);
        returnBase.setSuccess(false);
        returnBase.setData(null);
        return returnBase;
    }


    private static String getErrorMessage(int errorCode, Object... args) {
        String key = "errorCode." + errorCode;
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("message");
        messageSource.setDefaultEncoding("UTF-8");
        return getKeyMessage(messageSource, key, args);
    }

    private static String getKeyMessage(MessageSource messageSource, String key, Object... args) {
        try {
            return messageSource.getMessage(key, args, Locale.getDefault());
        } catch (NoSuchMessageException ex) {
            log.error(ex.getMessage(), ex);
            return "";
        }

    }

    /**
     * Gets success.
     *
     * @return the success
     */
    public boolean getSuccess() {
        return success;
    }

    /**
     * Sets success.
     *
     * @param success the success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Gets error.
     *
     * @return the error
     */
    public Error getError() {
        return error;
    }

    /**
     * Sets error.
     *
     * @param error the error
     */
    public void setError(Error error) {
        this.error = error;
    }

    /**
     * Gets total page.
     *
     * @return the total page
     */
    public long getTotalPage() {
        return totalPage;
    }

    /**
     * Sets total page.
     *
     * @param totalPage the total page
     */
    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * Gets total count.
     *
     * @return the total count
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * Sets total count.
     *
     * @param totalCount the total count
     */
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}