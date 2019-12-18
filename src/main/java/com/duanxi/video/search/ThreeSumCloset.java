package com.duanxi.video.search;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2019/12/18 20:47
 * leetcode 16 最接近三数之和
 * <p>
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * <p>
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ThreeSumCloset {
    // 题意说只存在唯一答案

    /**
     * 这里说的最接近也是可以利用双指针来解决这个问题
     * 因为只要我们将结果与target对比即可，这样最快，如果遍历的话时间复杂度太高
     * 注意对比的对象是target，因为是与它接近
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int i;
        int j;
        int k;
        int min = nums[0] + nums[1] + nums[2];
        for (i = 0; i < nums.length - 2; i++) {
            if (i > 1 && nums[i] == nums[i - 1]) continue;
            j = i + 1;
            k = nums.length - 1;
            // 什么叫做最接近
            while (k > j) {
                int temp = nums[j] + nums[k] + nums[i];
                if (Math.abs(temp - target) < Math.abs(min - target)) {
                    min = temp;
                }
                // 全都是跟target相比
                if (temp > target) {
                    k--;
                } else if (temp < target) {
                    j++;
                } else {
                    return target;
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        System.out.println(threeSumClosest(new int[]{0, 0, 0}, 1));
        System.out.println(threeSumClosest(new int[]{1, 1, 1, 0}, -100));
        System.out.println(threeSumClosest(new int[]{1, 1, -1, -1, 3}, -1));
        System.out.println(threeSumClosest(new int[]{0, 2, 1, -3}, 1));
    }
}
