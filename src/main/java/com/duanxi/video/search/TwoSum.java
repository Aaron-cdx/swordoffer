package com.duanxi.video.search;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caoduanxi
 * @Date 2019/12/17 20:23
 * leetcode 1 两数之和
 * <p>
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
    // 找到数字的下标返回
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 如果当前键不存在的话，就将自己的凑成target的值放入键，一旦下次遇到直接就是这两个数
            if (!map.containsKey(nums[i])) {
                map.put(target - nums[i], i);
            } else {
                // 遇到就直接返回
                return new int[]{map.get(nums[i]), i};
            }
        }
        return null;
    }
}
