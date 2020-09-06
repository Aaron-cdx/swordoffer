package com.duanxi.interview.wangyi;

import java.util.Scanner;

/**
 * @author caoduanxi
 * @Date 2020/8/8 14:47
 * 网易笔试
 * 即一个
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        // 输入数据的个数
        int n = scanner.nextInt();
        long[] nums = new long[n];
        int i = 0;
        while(n > 0){
            nums[i++] = scanner.nextLong();
            n--;
        }
        System.out.println(main.getMaxBroke(nums));
    }

    /**
     * 获得整数拆分后的最大值
     * 即判断当前数是否可以被2或者3整除
     */
    public long getMaxBroke(long nums[]){
        long maxCount = 0;
        for (long num : nums) {
            maxCount += getSuShu(num);
        }
        return maxCount;
    }
    public long getSuShu(long n){
        if(n == 1 || n <= 0) return 0;
        if(n == 2) return 1;
        if(n == 3) return 1;
        if(n % 2 == 0){
            return n / 2;
        }else{
            long x = n % 2;
            long y = n / 2;
            return x+y-1;
        }
    }
}
