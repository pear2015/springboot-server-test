package com.gs.crms.common.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by zhengyali on 2017/12/28.
 */
public class HelpUtil {
    /**
     * 构造
     */
    private HelpUtil() {
    }
    /**
     *将字符串中首字母转化为大写
     * @param name
     * @return
     */
    public static String  firstCharToUpperCase(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        Matcher m = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(name);
        while (m.find()) {
            stringBuilder.append((new StringBuilder()).append(m.group(1).toUpperCase() + m.group(2).toLowerCase())).append(" ");
        }
        return  stringBuilder.toString().trim();
    }
}
