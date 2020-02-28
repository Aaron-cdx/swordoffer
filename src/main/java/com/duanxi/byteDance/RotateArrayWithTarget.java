package com.duanxi.byteDance;

/**
 * @author caoduanxi
 * @Date 2020/2/27 21:11
 * 字节跳动：数组翻转
 * <p>
 * 题目：
 * I: 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。空间复杂度要求O(1)
 * <p>
 * II: 给定数组和target，每target长度反转一次
 */
public class RotateArrayWithTarget {
    /**
     * 第一种方法：只要存在一直翻转减缓。时间复杂度过高！
     * 通过所有用例，超出时间限制
     * 时间复杂度：O(n*k)  空间复杂度：O(1)
     * <p>
     * 可以使用空间复杂度为O(n)的方法，也就是直接将所有的元素都放置到最终的位置即可
     * 借助一个中间数组
     */
    private void rotate_I(int[] nums, int k) {
        if (nums.length == 1) return;
        int n = nums.length;
        k %= nums.length;
        while (k > 0) {
            int j = n - 1;
            while (j > 0) {
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = temp;
                j--;
            }
            k--;
        }
    }

    /**
     * 第二种方法：翻转三次，翻转部分即可
     * 时间复杂度：O(n)   空间复杂度：O(1)
     */
    public void rotate_I_2(int[] nums, int k) {
        if (nums.length == 1) return;
        k %= nums.length;
        // 翻转所有的
        reverse(nums, 0, nums.length - 1);
        // 翻转前k个
        reverse(nums, 0, k);
        // 翻转后面所有的
        reverse(nums, k + 1, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (end > start) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        RotateArrayWithTarget test = new RotateArrayWithTarget();
//        int[] ints = {1, 2, 3, 4, 5, 6, 7};
        int[] ints = {1, 2};
        test.rotate_I(ints, 3);
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }
}
