package com.gs.crms.common.utils;

import com.gs.crms.common.enums.SexType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by zhengyali on 2017/12/26.
 */
public class SexUtil {
    @Value("${citizen.unKnowSexId}")
    private static String unKnowSexId;
    @Value("${citizen.unKnowCrimeSexId}")
    private static String unKnowCrimeSexId;

    private SexUtil() {
    }

    /**
     * 性别的枚举处理
     *
     * @param value
     * @return
     */
    public static String getNameByValue(String value) {
        String gender = null;
        switch (value) {
            case "1":
                gender = SexType.MALE.toString().toLowerCase();
                break;
            case "2":
                gender = SexType.FEMALE.toString().toLowerCase();
                break;
            default:
                break;
        }
        return gender;
    }

    /**
     * 性别处理 根据人口库提供的性别枚举获取犯罪信息的枚举
     *
     * @param sexId
     * @return
     */
    public static String getCrimeSexId(String sexId) {
        if (StringUtils.isEmpty(sexId)||StringUtils.equals(SexUtil.unKnowSexId, sexId)) {
            return null;
        } else {
            return sexId;
        }
    }
}
