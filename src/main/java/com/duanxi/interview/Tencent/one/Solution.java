package com.duanxi.interview.Tencent.one;


import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/9/1 8:22
 * leetcode 128、最长连续序列
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] ints = solution.longestConsecutiveII(new int[]{1, 2, 0, 1});
//        int[] ints = solution.longestConsecutiveII(new int[]{9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6});
        int[] ints = solution.longestConsecutiveII(new int[]{100, 4, 200, 1, 3, 2});
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }


        System.out.println("---------");
        System.out.println(solution.longestConsecutiveIII(new int[]{9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6}));
    }

    /**
     * 获取最长连续子序列
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                int left = map.getOrDefault(num - 1, 0);
                int right = map.getOrDefault(num + 1, 0);
                int sum = left + right;
                map.put(num, sum + 1);
                // 同时需要更新左边和右边
                if (left != 0) {
                    // 更新当前数的左边
                    map.put(num - left, sum + 1);
                }
                if (right != 0) {
                    map.put(num + right, sum + 1);
                }
                max = Math.max(sum + 1, max);
            }
        }
        return max;
    }


    /**
     * 使用动态规划需要分析清楚当前的一个问题
     * 就是什么时候执行递增操作
     */
    public int[] longestConsecutiveII(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        Arrays.sort(nums);
        int max = Integer.MIN_VALUE;
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            // 这里注意就算啥都不干 dp[i]位置也要为1
            dp[i] = 1;
            if (nums[i] - nums[i - 1] == 1) {
                dp[i] = dp[i - 1] + 1;
            } else if (nums[i] == nums[i - 1]) {
                dp[i] = dp[i - 1];
            }
        }
        // 找到最大值的下标，然后找到最长序列的最大值即可
        int index = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
                index = nums[i];
            }
        }
        int[] num = new int[max];
        int j = 0;
        // 找出当前的一个序列
        for (int i = index - max + 1; i <= index; i++) {
            num[j++] = i;
        }
        return num;
    }

    // O(n) 这个如果最小值是在最后的话，可能的时间复杂度是O(n^2)
    public int longestConsecutiveIII(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longest = 0;
        int current;
        for (Integer num : set) {
            int longLen = 1; // 最短为1,即只从最短的算
            // 那么当前数可能是最最小的的,从最小找到最大，然后当前数只要存在比自己晓得直接跳过
            if (!set.contains(num - 1)) {
                current = num;
                while (set.contains(current + 1)) {
                    current += 1;
                    longLen += 1;
                }
            }
            longest = Math.max(longest, longLen);
        }
        return longest;
    }
}
