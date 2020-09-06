package com.duanxi.interview.Yuanfudao;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author caoduanxi
 * @Date 2020/8/1 18:59
 * 猿辅导笔试
 * 选了N门课程
 * 课程有开始和结束时间S E
 * 但是小猿可以一心多用，如何用最少的多用，上完所有的课程
 * =====>
 * 4
 * 1 4
 * 1 2
 * 2 3
 * 3 4
 * =====> 输出为2
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int volume = n;
        int[] start = new int[n];
        int[] end = new int[n];
        int[] process = new int[n];
        int i = 0;
        while (n > 0) {
            start[i] = scanner.nextInt();
            end[i] = scanner.nextInt();
            process[i] = end[i] - start[i];
            i++;
            n--;
        }
//        for (int i1 : start) {
//            System.out.print(i1 + " ");
//        }
//        System.out.println();
//        for (int i1 : end) {
//            System.out.print(i1 + " ");
//        }
//        System.out.println();
//        for (int process1 : process) {
//            System.out.print(process1 + " ");
//        }
//        System.out.println();
        System.out.println(main.getMinHeart(volume,process));
    }

    public int getMinHeart(int volume, int[] process) {
        if (process == null || process.length == 0) return 0;
        int[] dp = new int[volume+1];
        Arrays.fill(dp,volume+1);
        Arrays.sort(process);
        dp[0] = 0;
        for (int i = 0; i < process.length; i++) {
            for (int j = 1; j <= volume; j++) {
                if(j >= process[i]){
                    int k = j % process[i];
                    if (k == 0) {
                        k = j / process[i];
                        dp[j] = Math.min(dp[j], k);
                    } else {
                        k = j / process[i];
                        dp[j] = Math.min(dp[j], dp[j - k * process[i]] + dp[k * process[i]]);
                    }
                }
            }
        }
        return dp[volume];
    }

}
