package com.duanxi.video.array;

/**
 * @author caoduanxi
 * @2019/12/8 19:08
 * leetcode 209 长度最小的子数组
 * <p>
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 * <p>
 * 示例: 
 * <p>
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * 进阶:
 * <p>
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimunSizeSubarraySum {
    public static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // 使用滑动窗口的解法来解决题目
        int res = nums.length + 1;
        int i = 0;
        int j = -1; // [i...j]为了确保里面刚开始没有元素，必须在这里需要限定j=-1,j只管+，i只管减
        int sum = 0;
        while (i < nums.length) {
            // 防止数组越界
            if ((j + 1) < nums.length && sum < s) {
                sum += nums[++j];
            } else {
                sum -= nums[i++];
            }
            if (sum >= s) {
                res = Math.min(res, j - i + 1);
            }
        }
        return res == (nums.length + 1) ? 0 : res;
    }

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(4, new int[]{1, 4, 4}));
        System.out.println(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(minSubArrayLen(11, new int[]{1, 2, 3, 4, 5}));
    }
}
