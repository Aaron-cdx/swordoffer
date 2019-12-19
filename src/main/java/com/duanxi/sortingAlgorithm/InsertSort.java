package com.duanxi.sortingAlgorithm;

/**
 * @author caoduanxi
 * @Date 2019/12/19 14:41
 * 插入排序
 * <p>
 * 思想：如果是正序排列的话就一直前进，如果遇到小的数，此时就交换，交换之后需要一直检查到之前所有的数
 * <p>
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 * 稳定性：稳定
 */
public class InsertSort {
    public static void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
                swap(nums, j, j - 1);
            }
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
        insertSort(nums);
        System.out.print("[ ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }
}
