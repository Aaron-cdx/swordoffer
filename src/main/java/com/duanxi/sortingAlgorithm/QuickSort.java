package com.duanxi.sortingAlgorithm;

/**
 * @author caoduanxi
 * @Date 2019/12/19 15:32
 * 排序-快速排序
 * <p>
 * 思想：选定一个基准值，将大于基准值的放在后半部分，小于基准值的放在前半部分
 * 使用从后向前找大于基准值的，从前向后找小于基准值的
 * 依次采用二分法递归下去，可以将顺序排好
 * <p>
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(logn)
 * 稳定性：不稳定
 */
public class QuickSort {
    public static void quickSort(int[] nums) {
        quick(nums, 0, nums.length - 1);
    }
    private static void quick(int[] nums, int i, int j) {
        int l = i;
        int h = j;
        int temp = nums[l];
        while (l < h) {
            // 向前找到小于temp值的元素
            while (nums[h] > temp && h > l) {
                h--;
            }
            // 向后找到大于temp值的元素
            while (nums[l] < temp && l < h) {
                l++;
            }
            if (nums[l] == nums[h] && l < h) {
                l++;// 结束循环
            } else {
                swap(nums, l, h);
            }
        }
        // 结束的时候，以l,h为界的前面一般都是小于的，后面一半都是大于的
        // l==h
        if (l - 1 > i) quick(nums, i, l - 1);
        if (h + 1 < j) quick(nums, h + 1, j);
    }

    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{12, 15, 9, 20, 6, 31};
        quickSort(nums);
        System.out.print("[ ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }
}
