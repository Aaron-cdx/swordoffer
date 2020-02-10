package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/10 18:12
 * 牛客网：替换空格
 * <p>
 * 题目：
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class ReplaceSpace {
    public String replaceSpace(StringBuffer str) {
        String pattern = "\\s";
        String string = str.toString();
        string = string.replaceAll(pattern,"%20");
        return string;
    }
}
