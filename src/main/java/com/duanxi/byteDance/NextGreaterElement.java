package com.duanxi.byteDance;

import java.util.HashMap;
import java.util.Stack;
import java.util.Map;

/**
 * @author caoduanxi
 * @Date 2020/2/26 20:33
 * 字节面试题：数组中下一个更大的数
 * <p>
 * 题目：
 * I: 两个数组，找出第一个数组中的数在第二个当前下标位置是否有比它大的数。
 * 例如：num1=[4,1,2],num2=[1,3,4,2] 返回[-1,3,-1]
 * <p>
 * II: 给定一个循环的数组，找出下一个更大的数，注意可循环，如果没有大于自己的，则返回-1，否则返回大于自己的数
 */
public class NextGreaterElement {
    /**
     * 暴力解法：时间复杂度O(n^2)
     */
    public int[] nextGreaterElementI1(int[] nums1, int nums2[]) {
        if (nums1 == null || nums2 == null) return null;
        // 最笨的方法，遍历第二个数组
        /**
         * 理解题意：题目的意思是，第一个数组是第二个数组的子集，找出第一个数组中的数对应着第二个数组中位置
         * 是否有下一个更大的数，有则返回那个数，否则返回-1
         */
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = findNextGreater(nums1[i], nums2);
        }
        return nums1;
    }

    private int findNextGreater(int num, int nums[]) {
        for (int i = 0; i < nums.length; i++) {
            if (num == nums[i]) {
                int j = i;
                while (j < nums.length) {
                    if (nums[j] > num) {
                        return nums[j];
                    }
                    j++;
                }
            }
        }
        return -1;
    }

    /**
     * 使用栈结合map:时间复杂度：O(M+N),空间复杂度：O(n)
     */
    public int[] nextGreaterElementI2(int[] nums1, int nums2[]) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[nums1.length];
        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peek()) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    // 循环，我怎么判断他真的到了顶点呢？
    // 1 2 1
    // 1 -> 2
    // 2 -> 1 -> 1 -1
    // 1 -> 1 -> 2 2

    /**
     * 第一种方法，对于每个循环都去判断。每个都循环一次，直到碰到自己停止
     */
    public int[] nextGreaterElementII1(int[] nums) {
        if (nums == null) return null;
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            while (j % (nums.length) != i) {
                if (nums[j % nums.length] > nums[i]) {
                    res[i] = nums[j % nums.length];
                    break;
                }
                j++;
            }
            if (j % nums.length == i) {
                res[i] = -1;
            }
        }
        return res;
    }

    /**
     * 第二种方法：使用栈来实现
     *          0 1 2 3 4
     * 模拟过程：3 8 4 1 2 首先从2开始
     *         8 -1 -1 2 -1
     * 第二遍：此时栈中有3 8；到1时，此时栈中有2 3 8；到4时，1 2 3 8；到8时由于弹出完全，为空；
     * 到3时，只剩下8了
     * 3 8 4 1 2
     * 8 -1 8 2 3
     *
     * 时间复杂度：O(N) 空间复杂度：O(N)
     */
    public int[] nextGreaterElementII2(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums.length];
        for (int i = 2 * nums.length - 1; i >= 0; i--) {
            // 栈中只保留比自己大的数
            // 由于可以做循环，所以只需要来两次即可将所有的元素都放入栈中，只保留比自己大的元素
            while (!stack.isEmpty() && nums[i % nums.length] >= nums[stack.peek()]) {
                stack.pop();
            }
            // 现在找到了比自己大的,根据stack是否为空来判定是否存在比自己大的值
            // 如果到了栈为空，表名没有比自己大的元素，否则当前栈顶就是第一个比自己大的元素
            res[i % nums.length] = stack.isEmpty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length);
        }
        return res;
    }

    public static void main(String[] args) {
        NextGreaterElement test = new NextGreaterElement();
//        int[] ints = test.nextGreaterElementII2(new int[]{3, 8, 4, 1, 2});
//        for (int anInt : ints) {
//            System.out.print(anInt + " ");
//        }
        int[] ints = test.nextGreaterElementI1(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2});
        for (int anInt : ints) {
            System.out.print(anInt+" ");
        }
    }
}
