package com.duanxi.sortingAlgorithm;

/**
 * @author caoduanxi
 * @Date 2019/12/19 15:05
 * 排序-二分插入排序
 * <p>
 * 思想：
 * 找出具体需要比对的数是在哪，找到区间，实现区间平移，最后将temp放在low位置就好，二分法最后一定到达一个位置
 * <p>
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 * 稳定性：稳定
 */
public class BinaryInsertionSort {
    // 相比直接插入排序，这里使用折半比较，如果大于前半部分，则在后半部分插入，否则在前半部分插入
    // 因为前面i个已经排好序了
    public static void binaryInsertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int low = 0;
            int high = i - 1;
            int mid;
            while (low <= high) {
                mid = low + (high - low) / 2;
                // 大于的话只能在之前插入，否则就在后面插入
                if (nums[mid] > temp) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // 与j交换的不能够在low值之前，因为low就是区间的最小
            // 这是要平移所有的数
            for (int j = i - 1; j >= low; j--) {
                nums[j + 1] = nums[j];
            }
            nums[low] = temp;
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
        binaryInsertionSort(nums);
        System.out.print("[ ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }
}
