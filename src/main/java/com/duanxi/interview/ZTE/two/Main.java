package com.duanxi.interview.ZTE.two;

import java.util.Scanner;

/**
 * @author caoduanxi
 * @Date 2020/8/31 10:00
 */
public class Main {
    private static int count = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int n = scanner.nextInt();

            long[] arr = new long[n];
            for (int j = 0; j < n; j++) {
                arr[j] = scanner.nextInt();
            }
//            if (n <= 2) {
//                System.out.println(0);
//                continue;
//            }
            // 每次进行一个处理就好
            main.getOperateCnt(arr, 0, 0);
            if (count == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(count);
                count = Integer.MAX_VALUE;
            }
        }
    }

    /**
     * 如何将这n个数变换成一个等差数列？？？？？？
     * 使用最小的变换次数
     * 只能使用回溯的方法
     */
    private void getOperateCnt(long[] arr, int cnt, int index) {
        if (index == arr.length) {
            if (judgement(arr)) {
                count = Math.min(count, cnt);
//                System.out.println("****排列好的数组****");
//                for (int i : arr) {
//                    System.out.print(i + " ");
//                }
//                System.out.println();
            }
            return;
        }
        for (int i = index; i < arr.length; i++) {
            // 可以不变
            getOperateCnt(arr, cnt, i + 1);
            // 可以加一
            long temp = arr[i];
            arr[i] = arr[i] + 1;
            getOperateCnt(arr, cnt + 1, i + 1);
            // 注意还原
            arr[i] = temp;
            // 可以减一
            temp = arr[i];
            arr[i] = arr[i] - 1;
            getOperateCnt(arr, cnt + 1, i + 1);
            arr[i] = temp;
        }
    }

    private boolean judgement(long[] arr) {
        for (int i = 2; i <= arr.length - 1; i++) {
            if (arr[i] - arr[i - 1] != arr[i - 1] - arr[i - 2]) {
                return false;
            }
        }
        return true;
    }
}
