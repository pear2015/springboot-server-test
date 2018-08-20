package com.gs.crms.common.utils;

import com.gs.crms.common.enums.MarriageType;
/**
 * Created by zhengyali on 2017/12/26.
 */
public class MarriageUtil {
    private MarriageUtil() {
    }

    /**
     * MarriageId 处理
     */
    public static String getCrimeMarriageId(String marriageId) {
        String crimeMarriageId="5";
        switch (marriageId) {
            case "1":
            case "2":
                crimeMarriageId = "2";
                break;
            case "3":
            case "4":
                crimeMarriageId = "3";
                break;
            case "5":
            case "6":
                crimeMarriageId = "1";
                break;
            case "7":
            case "8":
                crimeMarriageId = "4";
                break;
            default:
             break;
        }
        return crimeMarriageId;
    }

    /**
     * 获取婚姻的枚举名称
     */
    public static String getMarriageName(String marriageId) {
        String marriageName=MarriageType.UNKNOW.toString();
        switch (marriageId) {
            case "1":
                marriageName = MarriageType.UNMARRIED.toString().toLowerCase();
                break;
            case "2":
                marriageName = MarriageType.MARRIED.toString().toLowerCase();
                break;
            case "3":
                marriageName = MarriageType.DIVORCED.toString().toLowerCase();
                break;
            case "4":
                marriageName = MarriageType.WIDOWER.toString().toLowerCase();
                break;
            case "5":
                marriageName = MarriageType.UNKNOW.toString().toLowerCase();
                break;
            default:
                break;
        }
        return marriageName;
    }
}
