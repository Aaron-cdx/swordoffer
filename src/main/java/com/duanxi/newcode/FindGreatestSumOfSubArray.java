package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/1/13 21:29
 * 连续子数组的最大和
 * <p>
 * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式识别中,
 * 常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
 * 但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},
 * 连续子向量的最大和为8(从第0个开始,到第3个为止)。
 * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
 */
public class FindGreatestSumOfSubArray {
    // 主要是判断累加的递增量是增加还是减少，如果是减少的话且小于数据本身，就选取数据本身，然后往后运算，
    public int FindGreatestSumOfSubArray(int[] array) {
        // 用一个数记录增量
        int incr = array[0];
        int res = array[0];
        for (int i = 1; i < array.length; i++) {
            // 判定前面是负增长的话，就利用当前的array[i]值即可
            // 1 -2 3 1+(-2)=-1 -1+3 < 3 那就直接抛弃前面的1 -2 直接使用3
            incr = Math.max(incr + array[i], array[i]);
            res = Math.max(res, incr);
        }
        return res;
    }

    public int findTwo(int[] array) {
        // 用动态规划来处理
        int[] dp = new int[array.length];
        dp[0] = array[0];
        // 动态规划主要是因为它利用了前面提供的参数
        for (int i = 1; i < array.length; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = array[i];
            } else {
                dp[i] = dp[i - 1] + array[i];
            }
        }
        // 这里需要多求一次
        for (int i = 1; i < dp.length; i++) {
            dp[0] = Math.max(dp[0], dp[i]);
        }
        return dp[0];
    }

    public static void main(String[] args) {
        FindGreatestSumOfSubArray findGreatestSumOfSubArray = new FindGreatestSumOfSubArray();
        System.out.println(findGreatestSumOfSubArray.FindGreatestSumOfSubArray(new int[]{1, -2, 3, 10, -4, 7, 2, -5}));
        System.out.println(findGreatestSumOfSubArray.findTwo(new int[]{1, -2, 3, 10, -4, 7, 2, -5}));
    }
}
