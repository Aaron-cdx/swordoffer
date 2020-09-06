package com.duanxi.interview.MeiTuan.four;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author caoduanxi
 * @Date 2020/8/15 17:21
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main= new Main();
        int n = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        int[] A = new int[n];
        int[] B = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
            B[i] = scanner.nextInt();
        }

        System.out.println(main.getMaxProfit(A,B,a,b));
    }

    /**
     * a,b表示A和B所需要的车
     */
    private int getMaxProfit(int[] A, int[] B, int a, int b) {
        Arrays.sort(A);
        Arrays.sort(B);
        int sumA = 0;
        for (int i = 0; i < a; i++) {
            sumA += A[A.length-1-i];
        }
        int sumB = 0;
        for (int i = 0; i < b; i++) {
            sumB += B[B.length-1-i];
        }
        return sumA+sumB;
    }
}
class Profit{
    int A;
    int B;
    int profix;

    public Profit(int a, int b, int profix) {
        A = a;
        B = b;
        this.profix = profix;
    }
}
