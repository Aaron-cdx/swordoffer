package com.duanxi.video.array;

/**
 * @author caoduanxi
 * @2019/12/2 20:15
 * leetcode 283
 * 将数组中所有为零的元素移动到数组的末尾
 * [0,1,0,3,12] --> [1,3,12,0,0]
 */
public class MoveZeroes {
    // 使用两个数组可以完成，这里不写，空间复杂度O(n)
    // 双指针来完成
    public static void moveZeroes(int[] nums) {
        int k = 0;
        int n = nums.length;
        // k保持赋值的位置
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                nums[k++] = nums[i];
            }
        }
        // 将最后两个元素置零
        for (int i = k; i < n; i++) {
            nums[i] = 0;
        }
    }

    // 继续优化，通过交换的方式，这样就不用最后的赋值0
    public static void moveZeroes2(int[] nums) {
        int k = 0;
        int n = nums.length;
        // k保持赋值的位置
        for (int i = 0; i < n; i++) {
            // 这里注意，如果测试用例都是非零元素，不用交换
            if (nums[i] != 0) {
                if (k != i)
                    swap(nums, k++, i);
                else
                    k++;
            }
        }
    }

    public static void swap(int nums[], int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        moveZeroes2(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
