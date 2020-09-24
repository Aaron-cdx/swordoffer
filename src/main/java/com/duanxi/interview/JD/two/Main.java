package com.duanxi.interview.JD.two;

import java.util.Scanner;

/**
 * @Author: caoduanxi
 * @Date: 2020/9/17
 * 多重背包问题
 * 购买一件道具获得一定的魅力值
 * 每种道具的价格和魅力值，总价格不超过上限的情况下使得所购买的道具的魅力值之和达到最大
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 每种道具的种类数
        int n = scanner.nextInt();
        // 总价值上限p
        int p = scanner.nextInt();
        // 多重背包问题
        int[] numbers = new int[3];
        int[] price = new int[3];
        int[] value = new int[3];
        int cnt = 0;
        for (int j = 0; j < n; j++) {
            numbers[cnt] = scanner.nextInt();
            price[cnt] = scanner.nextInt();
            value[cnt] = scanner.nextInt();
            cnt += 1;
        }
        int[][] f = new int[4][p + 1];
        // 利用数量的限制将多重背包问题编程单纯的背包问题
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <= p; j++) {
                // 这里主要计算的是当前的数量需要小于最大的数量，且花费需要小于限定的花费
                for (int k = 0; k <= numbers[i] && k * price[i] <= j; k++) {
                    f[i + 1][j] = Math.max(f[i + 1][j], f[i][j - price[i] * k] + value[i] * k);
                }
            }
        }
        System.out.println(f[3][p]);
    }
}
