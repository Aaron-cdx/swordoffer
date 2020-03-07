package com.duanxi.interview;

/**
 * @author caoduanxi
 * @Date 2020/1/7 10:25
 * 面试题：2020/1/7 自如面试
 * <p>
 * 题目：
 * 给定一个数组，数组中有若干个整数，每次遇到一个整数就往后跳整数大小相应的步数
 * 问有多少种跳法？
 * <p>
 * 给定一个含有n个数的数组，每次可以跳1次,2次,...n次，求有多少种跳法？
 */
public class Jump {
    public int minNumberOfJumps(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n; i++) {
            dp[i] = dp[i - 1] * 2;
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        Jump jump = new Jump();
        int[] arrs = new int[]{1, 2, 4, 6, 2, 3};
        int res = jump.minNumberOfJumps(arrs.length);
        System.out.println(res);
    }
}
