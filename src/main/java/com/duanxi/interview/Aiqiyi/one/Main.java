package com.duanxi.interview.Aiqiyi.one;

import java.util.Scanner;

/**
 * @author caoduanxi
 * @Date 2020/8/23 15:15
 * 求一个正整数的阶乘末尾有多少个0
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        int n = scanner.nextInt();
        long num = main.getMulti(n);
        int cnt = 0;
        while(n != 0){
            cnt += n/5;
            n /= 5;
        }
        System.out.println(cnt);
    }

    private long getMulti(int n) {
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = i * dp[i - 1];
        }
        for (int i = 1; i < dp.length; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();
        return dp[n];
    }
}
