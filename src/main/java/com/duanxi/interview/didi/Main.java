package com.duanxi.interview.didi;

import java.util.Scanner;

/**
 * @author caoduanxi
 * @Date 2020/9/2 15:45
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(getNum(n));
    }

    public static int getNum(int n) {
        if (n <= 0 || n > 36) return 0;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        if (n == 1) return 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
