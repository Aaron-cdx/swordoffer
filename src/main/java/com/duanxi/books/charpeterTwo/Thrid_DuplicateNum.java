package com.duanxi.books.charpeterTwo;

/**
 * @author caoduanxi
 * @2019/12/2 22:05
 * 查找数组中重复的任意一个数字
 * <p>
 * 题目：
 * 在一个长度为n的数组中所有的数字都在0~n-1的范围内。
 * 数组中有些数字是重复的，但是不知道是哪些数字重复了
 * 找出数组中任一重复的数字
 * <p>
 * {2,3,1,0,2,5,3} 查找重复的数字并输出，空间复杂度O(1)
 */
public class Thrid_DuplicateNum {
    /**
     * 本题需要分析清楚题目的意思，注意其中的数字都限定了范围(限定了范围侧面来说是一种优化)
     * 而且如果没有思路先用复杂度较高的方法做，然后优化
     */
    public static boolean duplicateNum(int[] nums, int n) {
        // 判断边界条件
        if (nums == null || n == 0) return false;
        // 判断边界条件
        for (int i = 0; i < n; i++) {
            if (nums[i] > n - 1 || nums[i] < 0) {
                return false;
            }
        }
        // 逻辑处理，将所有本来属于这个位置上的数字交换回来，一旦有重复，返回true
        for (int i = 0; i < n; i++) {
            // 如果与自己当前下标不等，就一直交换，直到相等。
            // 如果自己与下标不等，但是与自己的原来下标相等，表示重复了
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return true;
                }
                // 交换自己与当前数字的顺序
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(duplicateNum(new int[]{2, 3, 1, 0, 2, 5, 3}, 7));
        System.out.println(duplicateNum(new int[]{0, 2, 1, 3, 4, 6, 5}, 7));
        System.out.println(duplicateNum(new int[]{0, 2, 1, 3, 4, 7, 5}, 7));
    }
}
