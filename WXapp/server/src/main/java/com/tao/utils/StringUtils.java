package com.tao.utils;

/**
 * @Author neotao
 * @Date 2018/9/6
 * @Version V0.0.1
 * @Desc
 */
public class StringUtils {
    public static void main(String[] args) {
        String str = "java怎么把asdasd字2233符44串中的asdasd的111汉字取出来";
        System.out.println(getNumbers(str));
    }

    public static String getChineseChars(String words) {
        String reg = "[^\u4e00-\u9fa5]";
        return words.replaceAll(reg, "");
    }
    public static String getNumbers(String words) {
        String reg = "[^0-9]";
        return words.replaceAll(reg, "");
    }
}
