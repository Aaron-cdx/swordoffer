package com.duanxi.sortingAlgorithm;

/**
 * @author caoduanxi
 * @Date 2019/12/19 16:20
 * 排序-希尔排序
 * <p>
 * 按照增量来排，知道增量为1的时候，插入排序可以辅助将所有的值排列正确
 *
 * 时间复杂度：O(n^1.5)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 */
public class ShellSort {
    public static void shellSort(int[] nums) {
        int gap = nums.length;
        while (true) {
            gap = gap / 2;
            for (int i = 0; i < gap; i++) {
                // 这里就是插入排序
                for (int j = i + gap; j < nums.length; j += gap) {
                    int temp = nums[j];
                    int k = j - gap;
                    while (k >= 0 && nums[k] > temp) {
                        nums[k + gap] = nums[k];
                        k -= gap;// 确保一定结束循环
                    }
                    nums[k + gap] = temp;
                }
            }
            if (gap == 1) {
                break;
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
        shellSort(nums);
        System.out.print("[ ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }
}
