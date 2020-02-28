package com.duanxi.byteDance;

import java.util.Random;

/**
 * @author caoduanxi
 * @Date 2020/2/28 9:25
 * 字节跳动：快速排序
 * <p>
 * 快速排序的思想：
 * 给定一个随机数，将其分割为两部分有序的，则是排好序了。
 * 首先选定随机数，从后往前找比随机数小的，与随机数交换，保证后面的大于随机数
 * 然后从前往后找比随机数大的，与随机数交换，保证前面的小于等于随机数。
 * 一直交替查找即可。到最后到了终点，也就是随机数。此时即可前后分别快排。
 * <p>
 * 时间复杂度：O(nlogn) 空间复杂度：O(logn)  稳定性：不稳定
 */
public class QuickSort {
    public void quickSort(int[] nums) {
        quick(nums, 0, nums.length - 1);
    }

    private void quick(int[] nums, int start, int end) {
        int l = start;
        int r = end;
        // 随机挑选出一个数，以这个数为界限，前面的小于自己，后面的大于自己
        int temp = nums[l];
        // 当他们相等的时候，表名查找结束
        while (l < r) {
            // 往前面一直找到小于这个temp的数
            while (nums[r] > temp && r > l) {
                r--;
            }
            // 往后找大于这个temp的数
            while (nums[l] < temp && r > l) {
                l++;
            }
            // 结束循环，只需要让l++,l就大于r,防止多个相等的数，所以需要加上l<h
            // 保证相等的时候大家都是这个数
            if (nums[l] == nums[r] && l < r) {
                l++;
            } else {
                // 两者交换
                swap(nums, l, r);
            }
        }
        // 如果两者相等了，此时再进行分区,保证至少两个元素
        if (l - 1 > start) quick(nums, 0, l);
        if (r + 1 < end) quick(nums, l + 1, nums.length - 1);
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        QuickSort test = new QuickSort();
        Random random = new Random();
        int[] nums = new int[20];
        for (int i = 0; i < 20; i++) {
            nums[i] = random.nextInt(100);
        }
        test.quickSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
