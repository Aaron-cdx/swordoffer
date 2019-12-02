package com.duanxi.video.array;

/**
 * @author caoduanxi
 * @2019/12/2 21:08
 * leetcode 80 删除排序数组中重复元素II 每个元素最多出现2次
 *
 * 给定 nums = [1,1,1,2,2,3],
 *
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 *
 * 你不需要考虑数组中超出新长度后面的元素
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveDuplicatesII {
    // 重复的元素最多出现两次
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        int b = 0;
        nums[b] = nums[0];
        for (int i = 1; i < nums.length; i++) {
        }
        return 0;
    }
}
