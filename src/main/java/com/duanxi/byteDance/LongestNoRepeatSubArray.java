package com.duanxi.byteDance;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author caoduanxi
 * @Date 2020/3/9 21:29
 * 字节跳动：最长不重复子串
 * <p>
 * 题目：
 * 最长不重复子串就是从一个字符串中找到一个连续子串，该子串中任何两个字符都不能相同，且该子串的长度是最大的。
 * <p>
 * absd   4
 * abba   2
 */
public class LongestNoRepeatSubArray {
    /**
     * 最长不重复子串，使用双指针，保持一个不重复元素的窗口即可
     * 窗口向后滑动，遇到重复的话，左边窗口向前滑动
     */
    public static int getLongest(String str) {
        if (str.length() == 0) return 0;
        Set<Character> set = new HashSet<>();
        int l = 0;
        int r = 0;
        int len = 0;
        while (r < str.length() && l <= r) {
            // 如果不包含的话，直接插入
            if (!set.contains(str.charAt(r))) {
                set.add(str.charAt(r++));
            } else {
                // 如果遇到相等，一定是左边第一个
                set.remove(str.charAt(l++));
            }
            len = Math.max(len, r - l);
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(getLongest("abcd"));
        System.out.println(getLongest("abbacddcefg"));
        System.out.println(getLongest("aaaa"));
    }
}
