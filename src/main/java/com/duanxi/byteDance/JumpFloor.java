package com.duanxi.byteDance;

/**
 * @author caoduanxi
 * @Date 2020/3/12 22:32
 * 字节跳动：跳台阶
 * <p>
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * <p>
 * 字节跳动：变态跳台阶
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * <p>
 * 字节跳动：矩形覆盖
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 * 比如n=3时，2*3的矩形块有3种覆盖方法：
 */
public class JumpFloor {
    /**
     * 直接动态规划：有规律即可
     */
    public int jumpFloor(int target) {
        if (target == 0) return 0;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= target; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[target - 1];
    }

    public int JumpFloorII(int target) {
        if (target == 0) return 0;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        dp[1] = 1;
//        dp[2] = 2;
        // 2的指数递增
        for (int i = 2; i <= target; i++) {
            dp[i] = 2 * dp[i - 1];
        }
        return dp[target];
    }

    public int RectCover(int target) {
        if (target < 1) return 0;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        dp[1] = 1;
        if(target == 1){
            return dp[1];
        }
        dp[2] = 2;
        for (int i = 3; i <= target; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[target];
    }
}
