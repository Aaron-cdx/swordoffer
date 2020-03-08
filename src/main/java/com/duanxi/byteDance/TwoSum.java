package com.duanxi.byteDance;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caoduanxi
 * @Date 2020/3/8 21:51
 * 字节跳动：两数之和
 * <p>
 * 题目：
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSum {
    /**
     * 总体来说两种解法吧，还有的就是使用两遍hash表的方法
     * 跟一遍hash基本一致，只不过是将添加元素的操作单独遍历一遍而已
     */


    /**
     * 第一种方法使用map结构来存储
     * 时间复杂度：O(n)  空间复杂度：O(n)
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        return null;
    }

    /**
     * 暴力法
     * 时间复杂度：O(n^2)    空间复杂度：O(1)
     */
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == target - nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("The array not have solution");
    }

}
