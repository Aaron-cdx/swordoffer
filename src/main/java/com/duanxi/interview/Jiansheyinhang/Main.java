package com.duanxi.interview.Jiansheyinhang;

import java.util.Scanner;

/**
 * @author caoduanxi
 * @Date 2020/7/19 14:56
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true){
            int n = sc.nextInt();
            int m = sc.nextInt();
            System.out.println(getMinRes(n,m));
        }
    }

    /**
     * 如果是2*2此时只要补给品丢到四个地方的交界处即可，此时补给提供的是1个即可
     * 如果是2*3呢，此时最少两个吧
     * 这里只需要计算一个规律即可
     * 即算出行和列，都向上取整即可
     */
    public static int getMinRes(int n, int m){
        if(n == 0||m==0) return 0;
        int x = (int)Math.ceil(n/2.0);
        int y = (int)Math.ceil(m/2.0);
        return x*y;
    }
}
