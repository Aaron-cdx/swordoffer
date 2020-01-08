package com.duanxi.interview;

/**
 * @author caoduanxi
 * @Date 2020/1/8 11:11
 * 面试题：滴滴
 * 有一组数字增序排列的数组，将后半部分反转到前半部分，问如何找到中位数target的下标
 * 要求时间复杂度O(logn)
 */
public class ReverseAndFindMidIndex {
    // 先查找，然后使用二分法
    public static int findTargetIndex(int[] nums, int target) {
        // 注意这里要获取的是下标
        if (nums.length == 0 || nums == null) return 0;
        int start = 0;
        int end = nums.length - 1;
        int mid = 0;
        while (start <= end) {
            mid = start + (end - start) / 2;
            // 如果是中位数直接等于的话，直接返回
            if (nums[mid] == target) {
                return mid;
            }
            // 表示还在反转的那一部分中，往后走是一定满足的，所以这里存在问题
            // 5 6 7 8 9 1 2 3 4 第一种情况
            // 9 10 1 2 3 4 5 6 7 8  第二种情况
            if (nums[mid] > nums[start]) {
                /*if (nums[mid] < target){
                    start = mid + 1;
                }else{
                    end = mid - 1;
                }*/
                // 最左侧元素 < target < nums[mid]
                if (nums[mid] > target && nums[start] < target ) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (nums[mid] < target && target < nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        // 都没找到就返回-1
        return -1;
    }

    /*// 直接二分法
    public static int binaryFindMidNum(int[] nums) {
        if (nums.length == 0 || nums == null) return 0;

        int l = 0;
        int r = nums.length - 1;
        int mid = 0;
        while (l + 1 < r) {
            mid = l + ((r - l) >> 1);
            // 两边都小于的话，表名本次的终点就是分割点
            if (nums[mid] > nums[l] && nums[mid] < nums[r]) {
                // 结果就是r
                return nums[r];
            } else { // 否则就判断是在前半部分，还是在后半部分
                // 表名左右都有反转的，本次的终点不是平衡中点
                if (nums[mid] < nums[r]) {
                    // 后半部分
                    l = mid;
                } else {
                    r = mid;
                }
            }
        }
        return Math.min(nums[r], nums[l]);
    }*/

    public static void main(String[] args) {
        System.out.println(findTargetIndex(new int[]{9, 10, 1, 2, 3, 4, 5, 6, 7, 8}, 7));
//        System.out.println(binaryFindMidNum(new int[]{9, 10, 1, 2, 3, 4, 5, 6, 7, 8}));
    }
}
