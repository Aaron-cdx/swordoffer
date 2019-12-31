package com.duanxi.video.search;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author caoduanxi
 * @Date 2019/12/31 11:21
 * <p>
 * leetcode 220 存在重复元素III
 * <p>
 * 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，
 * 使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1], k = 3, t = 0
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: nums = [1,0,1,1], k = 1, t = 2
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0) return false;
        for (int i = 0; i < nums.length; i++) {
            // 确保在k之内，遍历所有的数
            for (int j = Math.max(i - k, 0); j < i; j++) {
                if (Math.abs(nums[i] - nums[j]) <= t) return true;
            }
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length == 0 || nums == null) return false;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 获取这个地方的最大值
            Integer ceil = set.ceiling(nums[i]);
            if (ceil != null && ceil <= nums[i] + t) return true;

            Integer floor = set.floor(nums[i]);
            if (floor != null && nums[i] <= floor + t) return true;

            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
