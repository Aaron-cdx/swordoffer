package com.duanxi.newcode;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2020/2/6 13:18
 * 牛客网：翻转单词顺序
 * <p>
 * 题目：
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，
 * 写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，
 * 有一天他向Fish借来翻看，但却读不懂它的意思。
 * 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
 * 正确的句子应该是“I am a student.”。
 * Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 */
public class ReverseSentence {
    public String ReverseSentence(String str) {
        /**
         * 第一种方法：转换成字符串数组，交换拼接
         * 可以不交换拼接，从后往前即可
         */
        /*int n = str.trim().length();
        if (n == 0) return str;
        String[] strings = str.split(" ");
        int l = 0;
        int r = strings.length - 1;
        while (l < r) {
            String temp = strings[l];
            strings[l] = strings[r];
            strings[r] = temp;
            l++;
            r--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (i != strings.length - 1) {
                sb.append(strings[i]);
            } else {
                sb.append(strings[i]).append(" ");
            }
        }
        return sb.toString();*/
        /**
         * 第二种方法：剑指offer思想：使用两次翻转
         * 先翻转所有的字符，然后滑动窗口遇到' '就翻转
         * 最后到了最后一个字符，翻转所有break结束
         */
        if (str == null || str.trim().length() == 0) return str;
        char[] chars = str.toCharArray();
        reverseChars(chars, 0, str.length() - 1);
        // 利用滑动窗口
        // 遇到' '执行翻转
        int l = 0;
        int r = 0;
        while (l < str.length()) {
            if (chars[r] == ' ') {
                reverseChars(chars, l, r - 1);
                // 交换完之后,一起跳过' '
                r++;
                l = r;
            }
            if (r == str.length() - 1) {
                reverseChars(chars, l, r);
                // 到了最后交换玩就break，否则r会出现越界，可以在while中加对r的判断
                break;
            }
            r++;
        }
        return String.valueOf(chars);
    }

    private void reverseChars(char[] chars, int l, int r) {
        while (l < r) {
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
            l++;
            r--;
        }
    }

    public static void main(String[] args) {
        ReverseSentence test = new ReverseSentence();
        String s = test.ReverseSentence("I am a student.");
        System.out.println(s);
    }
}
