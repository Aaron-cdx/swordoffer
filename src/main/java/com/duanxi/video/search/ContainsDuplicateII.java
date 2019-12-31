package com.duanxi.video.search;

import java.util.HashSet;
import java.util.Set;

/**
 * @author caoduanxi
 * @Date 2019/12/31 11:05
 * <p>
 * leetcode 219 存在重复元素II
 * <p>
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
 * 使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ContainsDuplicateII {
    // 给出方法滑动窗口+查找表
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        // 要求nums[i]==nums[j]&& abs(j-i)<=k
        int i = 0;
        int j = 0;
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        while (j < n) {
            // 没有重复元素就返回true
            if (set.add(nums[j])) {
                j++;
            } else {
                // 出现重复元素
                if (Math.abs(j - i) <= k) {
                    return true;
                }
                // 否则继续滑动窗口,将第一个元素移除，然后移动窗口
                set.remove(nums[i]);
                i++;
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        // 要求nums[i]==nums[j]&& abs(j-i)<=k
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        // 维护一个长为k的哈希表，超过就删除元素
//        while (j < n) {
//            if (set.contains(nums[j])) {
//                return true;
//            }
//            set.add(nums[j]);
//            if (set.size() > k) {
//                set.remove(nums[j - k]);
//            }
//            j++;
//        }
        for (int j = 0; j < n; j++) {
            if (set.contains(nums[j])) {
                return true;
            }
            set.add(nums[j]);
            if (set.size() > k) {
                set.remove(nums[j - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
