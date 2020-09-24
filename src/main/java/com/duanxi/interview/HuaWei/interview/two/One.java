package com.duanxi.interview.HuaWei.interview.two;

/**
 * @Author: caoduanxi
 * @Date: 2020/9/9
 * 华为面试
 * 求最长回文子串
 * babad
 * <p>
 * cbbd
 */
public class One {
    public static void main(String[] args) {
        One test = new One();
        System.out.println(test.longestPalindrome("abababddb"));
    }

    /**
     * 从中心发散的思路解决当前问题
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int a = executeCenter(s, i, i);// 当前点，因为可能是回文的中点
            int b = executeCenter(s, i, i + 1);
            int len = Math.max(a, b);
            // 表示如果比之前的更长
            if (len > end - start) {
                //
                start = i - (len - 1) / 2;
                end = i + (len) / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int executeCenter(String str, int left, int right) {
        int l = left;
        int r = right;
        // 中心扩展思维
        while (l >= 0 && r < str.length() && str.charAt(l) == str.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }
}
