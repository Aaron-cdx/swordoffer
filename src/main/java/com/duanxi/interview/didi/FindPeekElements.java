package com.duanxi.interview.didi;

/**
 * @author caoduanxi
 * @Date 2020/9/2 9:32
 * leetcode 162、寻找峰值
 * 要求时间复杂度为O(logn)，也就是要求只能通过二分查找的方式来解决问题
 * 1 2 3 1 => 3
 * <p>
 * 1 2 1 3 5 6 4 => 6
 */
public class FindPeekElements {
    public int findPeakElement(int[] nums) {
        if (nums.length <= 1) return 0;
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int findMaxBinarySearch(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > nums[mid + 1]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }
}
