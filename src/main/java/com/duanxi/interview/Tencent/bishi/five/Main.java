package com.duanxi.interview.Tencent.bishi.five;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[2*n];
        String str = scanner.nextLine();
        for (int i = 0; i < 2 * n; i++) {
            nums[i] = scanner.nextInt();
        }
    }
}
