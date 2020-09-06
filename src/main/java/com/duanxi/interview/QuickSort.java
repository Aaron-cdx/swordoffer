package com.duanxi.interview;

/**
 * @author caoduanxi
 * @Date 2020/9/3 17:58
 */
public class QuickSort {
    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        int[] nums = new int[]{1, 3, 4, 6, 2, 34, 6, 3, 12, 34};
        sort.quickSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public void quickSort(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int start, int end) {
        if (start > end) return;
        int l = start;
        int r = end;
        int temp = nums[l];
        while (l < r) {
            // 从后往前找到第一个小于当前的值
            while (l < r && nums[r] > temp) {
                r--;
            }
            // 从前往后找第一个大于自己的
            while (l < r && nums[l] < temp) {
                l++;
            }
            // 想等就往后走
            if (l < r && nums[l] == nums[r]) {
                l++;
            } else {
                swap(nums, l, r);
            }
        }
        // 这里就利用当前的一个数用来作为基准
        if (l - 1 > start) quickSort(nums, start, l - 1);
        if (r + 1 < end) quickSort(nums, l + 1, end);
    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
