package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/7 11:16
 * 牛客网：把字符串转换成整数
 * <p>
 * 题目：
 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。
 * 数值为0或者字符串不是一个合法的数值则返回0
 * <p>
 * 输入描述：
 * 输入一个字符串,包括数字字母符号,可以为空
 * <p>
 * 输出描述：
 * 如果是合法的数值表达则返回该数字，否则返回0
 */
public class StrToInt {
    public int StrToInt(String str) {
        // 数值为0或者字符串不是一个合法的数值都返回0
        if (str == null || str.length() == 0) return 0;
        // 现在考虑的只有进位的问题。每次进10倍数
        long res = 0;
        int len = str.length() - 1;
        // 一直判断至第一位，不是数字直接返回0
        for (int i = len; i >= 1; i--) {
            if (!Character.isDigit(str.charAt(i))) {
                return 0;
            }
            // 计算结果
            res += Math.pow(10, len - i) * (str.charAt(i) - '0');
        }
        // 如果第一位为数字的话，计算res
        if (Character.isDigit(str.charAt(0))) {
            res += Math.pow(10, len) * (str.charAt(0) - '0');
        }
        // 如果第一位为负号，且值在2147483648L以内的话，添加负号返回
        if ('-' == (str.charAt(0)) && res <= 2147483648L) {
            return (int) res * (-1);
        }
        // 如果为0或者值2147483648L这个以外包括这个直接返回0
        if(res == 0 || res >= 2147483648L) return 0;
        // 否则返回res的值
        return (int) res;
    }

    public static void main(String[] args) {
        StrToInt test = new StrToInt();
        int i = test.StrToInt("-2147483648");
        System.out.println(i);
    }
}
