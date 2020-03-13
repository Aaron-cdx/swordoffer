package com.duanxi.byteDance;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2020/3/13 21:44
 * 字节跳动：最长上升子序列
 * <p>
 * 题目：
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * <p>
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n^2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(nlogn) 吗?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LenOfLongestAscendingSequence {
    public static int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (nums.length < 2) return len;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            res = Math.max(dp[i], res);
        }
        return res;
    }

    /**
     * 整体的思想就是获取到递增tail，
     * 如果遇到小于tail当前的数的时候，就判断当前的数是否更小，找个位置插下去，然后在向后移动
     * 因为越小可能后面的上升序列越长
     */
    public static int lengthOfLIS2(int[] nums) {
        int len = nums.length;
        if(len < 2) return len;
        // 因为tail[0]不存值的
        int[] tail = new int[len+1];
        int res = 1 ;
        tail[res] = nums[0];
        for (int i = 1; i < len; i++) {
            if(nums[i] > tail[res]){
                // 如果一直递增的话
                tail[++res] = nums[i];
            }else{
                // 递增停止遇到障碍了
                int l = 1;
                int r = res;
                int pos = 0;
                while(l <= r){
                    int mid = (l+r)>>1;
                    // nums[i] < nums[r]
                    if(tail[mid] < nums[i]){
                        pos = mid;
                        l = mid+1;
                    }else{
                        r = mid-1;
                    }
                }
                // 最后的话
                tail[pos+1] = nums[i];
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int res = lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7});
        System.out.println(res);
    }
}
