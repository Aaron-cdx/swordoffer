package com.duanxi.video.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2019/12/18 19:24
 * leetcode 18 四数之和
 * <p>
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
 * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FourSum {
    /**
     * 跟三数之和基本一致
     * 只是多了最外面的一重循环
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) return res;
        // bug案例[0,0,0,0]
        Arrays.sort(nums);
        int i;
        int j;
        int k;
        int l;
        for (i = 0; i <= nums.length - 4; i++) {
            // 前面去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (j = i + 1; j <= nums.length - 3; j++) {
                // 前面去重复
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                k = j + 1;
                l = nums.length - 1;
                int sum = (target - nums[i]) - nums[j];
                while (k < l) {
                    if (nums[k] + nums[l] > sum) {
                        l--;
                    } else if (nums[k] + nums[l] < sum) {
                        k++;
                    } else {
                        // 表示相等，此时去重，里面去重
                        int t = l - 1;
                        while (t > k && t > j) {
                            if (nums[t] != nums[l])
                                break;
                            t--;
                        }
                        res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[t + 1]));
                        k++;
                        l = t;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // [[-2, -1, 1, 2], [-1, 0, 0, 1]]
//        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
//        System.out.println(fourSum(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 0));
        System.out.println(fourSum(new int[]{0, 0, 0, 0}, 0));
        System.out.println(fourSum(new int[]{0, 0, 0, 0}, 1));
        System.out.println(fourSum(new int[]{-1, 0, 1, 2, -1, -4}, -1));

    }
}
