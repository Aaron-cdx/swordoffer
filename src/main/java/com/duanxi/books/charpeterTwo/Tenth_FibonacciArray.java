package com.duanxi.books.charpeterTwo;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @2019/12/13 21:37
 * 剑指offer 面试题10 斐波那契数列
 * <p>
 * 题目：
 * 写一个函数，输入n，求斐波那契数列的第n项。
 */
public class Tenth_FibonacciArray {
    // 教科书式递归解法
    public static long Fibornacci(int n){
        if(n <=  0) return 0;
        if(n == 1) return 1;
        return Fibornacci(n-1) + Fibornacci(n-2);
    }
    // 数据存储记忆法
    static long[] memo = null;
    public static long fibornacci(int n){
        memo = new long[n+1];
        Arrays.fill(memo,-1);
        return getFibornacci(n);
    }

    private static long getFibornacci(int n) {
        if(n <= 0) return 0;
        if(n == 1) return 1;
        if(memo[n] != -1) return memo[n];
        // 使用一个记忆化数组存储
        return memo[n] = getFibornacci(n-1) + getFibornacci(n-2);
    }

    // 第三种方法使用动态规划解决
    public static long fibornacciTwo(int n){
        if(n <= 0) return 0;
        if(n == 1) return 1;
        long dp[] = new long[n+1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(fibornacciTwo(400));
        long endTime = System.currentTimeMillis();
        System.out.println("耗费时间："+(endTime-startTime)+"ms");
    }
}
