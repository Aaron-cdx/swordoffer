package com.duanxi.books.charpeterThree;

/**
 * @author caoduanxi
 * @Date 2020/1/6 22:17
 * 剑指offer 面试题16 数值的整数次方
 * <p>
 * 题目：实现函数double Power(double base, int exponent)
 * 求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
 */
public class Power {
    // 注意一定要考虑到负系数的情况，以及边界为0的情况
    public static double power(double base, int exponent) {
        if (base == 0) return 1;
        boolean positive = false;
        if (exponent < 0) {
            // 表明是负指数
            positive = true;
        }
        // 负指数的话就要求倒数即可
        double res = 1;
        for (int i = 0; i < Math.abs(exponent); i++) {
            res *= base;
        }
        return positive ? 1 / res : res;
    }


    public static double optimizationPower(double base, int exponent) {
        boolean positive = false;
        /*使用java的话要注意判断exponent的正负情况*/
        if (exponent < 0) {
            positive = true;
            exponent = Math.abs(exponent);
        }
        if (exponent == 0) return 1;
        if (exponent == 1) return base;
        // 使用回溯法来求解问题
        double result = optimizationPower(base, exponent >> 1);
        result *= result;
        if ((exponent & 0x1) == 1) {
            result *= base;
        }
        return positive ? 1 / result : result;
    }

    public static void main(String[] args) {
        System.out.println(power(2, 3));
        System.out.println(optimizationPower(2, -3));
        System.out.println(Math.pow(2, -3));
    }
}
