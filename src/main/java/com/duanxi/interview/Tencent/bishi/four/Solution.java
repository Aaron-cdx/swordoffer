package com.duanxi.interview.Tencent.bishi.four;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: caoduanxi
 * @Date: 2020/9/7
 */
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (n == 2) {
            System.out.println(a[0]);
        }
        // 拷贝一份数组
        int[] t = Arrays.copyOf(a, n);
        Arrays.sort(t);
        int l = t[n / 2 - 1];
        int r = t[n / 2];
        // 这一步处理的很妙，因为中位数无非就是两个，只有轮到两个被删除的时候
        // 此时中位数才会被替换为对方，所以只需要判断当前值是否小于前一个中位数
        // 如果是的话，当前数在前面，则后面多一个位置，则中位数输出后面的
        // 如果当前的数不小于前一个中位数，表示当前数字在后面，则前面多出一个位置，输出前面的
        for (int i = 0; i < n; i++) {
            if (a[i] <= l) {
                System.out.println(r);
            } else {
                System.out.println(l);
            }
        }
    }
}
