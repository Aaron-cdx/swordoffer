package com.duanxi.books.charpeterTwo;

/**
 * @author caoduanxi
 * @2019/12/3 21:20
 * 找出数组中重复的元素，不能修改数组。
 * 使用空间为O(1)解决这个问题
 *
 * 使用二分法可以将区间中的值慢慢缩小，因为在一段区间内，根据题意，如果没有重复，数据就不会发生变化
 * 只有重复的情况才是会发生变化的
 * 根据发生的变化来获取其中出现重复数字的区间，进而通过二分缩小查找的数值范围
 * 二分法最后一定会定位到一个数，最终只要这个数有重复，就返回这个数，否则返回-1
 */
public class Third_DuplicateNumII {
    public static int fineDuplicateNum(int[] nums, int n) {
        if (nums == null || n < 1) {
            return -1;
        }
        int start = 1;
        int end = n - 1;
        // 使用二分法查找 这其中的end以及start是其中数字大小的边界，并不是数组中的边界
        while (end >= start) {
            int mid = ((end - start) >> 1) + start;
            // 计算在限定的区间中出现的次数
            int count = countRange(nums, n, start, mid);
            if (end == start) {
                if (count > 1)
                    return start;
                else
                    break;
            }
            // 这里就是判断这个重复的数字是在那个值的区间范围内
            if (count > (mid - start + 1)) // 前半段
                end = mid;
            else // 后半段
                start = mid + 1;
        }
        return -1;
    }

    // 将start和end限定，最后一定会定位到一个数字，如果这个数字大于1次，就是重复的那个数
    private static int countRange(int[] nums, int n, int start, int end) {
        if (nums == null) return 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= start && nums[i] <= end) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(fineDuplicateNum(new int[]{1, 2, 2, 3, 4, 5, 6, 7}, 8));
    }
}
