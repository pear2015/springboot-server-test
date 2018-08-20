package com.gs.crms.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * Created by zhengyali on 2017/12/27.
 */
public class Base64Util {
    private Base64Util() {
    }
    /**
     *base64字符串转byte[]
     * @param base64str
     * @return
     */
    public static  byte[] base64StringToByteFun(String base64str){
        return Base64.decodeBase64(base64str);
    }
    /**
     *byte[]转base64字符串
     * @param b
     * @return
     */
    public static  String  byteToBase64StringFun(byte[] b){
        return Base64.encodeBase64String(b);
    }
}
