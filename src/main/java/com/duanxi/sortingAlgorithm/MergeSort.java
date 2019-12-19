package com.duanxi.sortingAlgorithm;

/**
 * @author caoduanxi
 * @Date 2019/12/19 16:59
 * 排序-归并排序
 * <p>
 * 思想：利用二分来将数组分开成一个小序列，对于小序列使用合并，最后形成前后都有序
 * 合并即可
 * <p>
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(n)
 * 稳定性：稳定
 */
public class MergeSort {
    public static void mergeSort(int[] nums) {
        partition(nums, 0, nums.length - 1);
    }

    private static void partition(int[] nums, int start, int end) {
        if (start == end) return;
        int mid = start + (end - start) / 2;
        partition(nums, start, mid);// 左边有序
        partition(nums, mid + 1, end);// 右边有序
        merge(nums, start, mid, end);
    }

    private static void merge(int[] nums, int start, int mid, int end) {
        int[] p = new int[end - start + 1];
        int t = 0;
        int l = start;
        int r = mid + 1;
        while (l <= mid && r <= end) {
            if (nums[l] > nums[r]) {
                p[t++] = nums[r++];
            } else {
                p[t++] = nums[l++];
            }
        }
        while (l <= mid) {
            p[t++] = nums[l++];
        }
        while (r <= end) {
            p[t++] = nums[r++];
        }
        for (int i = 0; i < p.length; i++) {
            nums[start + i] = p[i];
        }
    }

    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{8, 7, 5, 3, 2, 1};
        int[] nums = new int[]{12, 15, 9, 20, 6, 31};
        mergeSort(nums);
        System.out.print("[ ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }
}
