package com.gs.crms.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhangqiang on 2017/11/11.
 * 字符串和列表互相转换工具类
 */
public class StringAndListExchangeUtil {
    private StringAndListExchangeUtil() {
    }
    /**
     * 将字符串列表转换为以逗号分隔的字符串
     *
     * @param list
     * @return
     */
    public static String changeIdListToString(List<String> list) {
        String ids = "";
        if (list != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    stringBuilder.append(list.get(i));
                } else {
                    stringBuilder.append(list.get(i) + ",");
                }
            }
            ids = stringBuilder.toString();
        }
        return ids;
    }
    /**
     * 将以逗号分隔的字符串转换为字符串列表
     *
     * @param str
     * @return
     */
    public static List<String> changeStringToList(String str) {
        List<String> stringList = new ArrayList<>();
        if (StringUtils.isNotBlank(str)) {
            String[] strArray = str.split(",");
            stringList = Arrays.asList(strArray);
        }
        return stringList;

    }
}
