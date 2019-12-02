package com.duanxi.video.array;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @2019/12/2 20:48
 * leetcode 26 删除排序数组中的重复项
 * 原地修改
 * 给定数组 nums = [1,1,2],
 *
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveDuplicates {
    public static int removeDuplicates(int[] nums) {
        // 数组注意判断边界条件
        if(nums.length == 0){
            return 0;
        }
        // 已经排序了
        int b = 0;
        nums[b] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[b] != nums[i]){
                // 用自己存，满足才存取
                nums[++b] = nums[i];
            }
        }
        return b+1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,0,1,1,2,2,2,3,3,4,4};
        int index = removeDuplicates(nums);
        for (int i = 0; i < index; i++) {
            System.out.println(nums[i]);
        }
    }
}
