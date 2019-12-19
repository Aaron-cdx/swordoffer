package com.duanxi.sortingAlgorithm;

/**
 * @author caoduanxi
 * @Date 2019/12/18 22:29
 * 冒泡排序算法
 * <p>
 * 思想：
 * 冒泡是每次让一个最大的数到达最后
 * 类似于泡泡从水里冒出来一样
 * <p>
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 */
public class BubbleSort {
    public static void bubbleSort(int[] nums) {
        // 冒泡排序，每一轮比较交换
        // 应该从前开始，因为每一轮比较之后，第一个数永远都是最小的
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i; j++) {
                if (nums[j] > nums[j + 1])
                    swap(nums, j, j + 1);
            }
            /*System.out.print("[ ");
            for (int num : nums) {
                System.out.print(num + " ");
            }
            System.out.println("]");*/
        }
    }

    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{8, 7, 5, 3, 2, 1};
        bubbleSort(nums);
        System.out.print("[ ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }
}
