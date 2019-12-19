package com.duanxi.sortingAlgorithm;

/**
 * @author caoduanxi
 * @Date 2019/12/19 14:22
 * 排序-选择排序
 * 每次选择一个最小的数到前面来
 *
 * 主要思想：每一轮选择一个最小的数放置第一个位置
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 */
public class SelectedSort {
    public static void selectedSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            int temp = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[temp])
                    temp = j;
            }
            // 不等的时候再交换
            if (temp != i) {
                swap(nums, i, temp);
            }
        }
    }

    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{8, 7, 5, 3, 2, 1};
        selectedSort(nums);
        System.out.print("[ ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }
}
