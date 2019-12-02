package com.duanxi.video.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @author caoduanxi
 * @2019/12/2 20:31
 * leetcode 27 移除元素问题
 * 给定 nums = [3,2,2,3], val = 3,
 *
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveElement {
    // 时间复杂度O(n)
    // 空间复杂度O(n)
    public static int removeElement(int[] nums, int val) {
        int b = 0;
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != val){
                res[b++] = nums[i];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = res[i];
        }
        return b;
    }
    // 时间复杂度O(n)
    // 空间复杂度O(1)
    public static int removeElement2(int[] nums, int val) {
        int b = 0;
        for (int i = 0; i < nums.length; i++) {
            // 不为val才交换，往前走
            if(nums[i] != val){
                nums[b++] = nums[i];
            }
        }
        return b;
    }

}
