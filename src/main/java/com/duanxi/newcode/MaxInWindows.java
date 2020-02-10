package com.duanxi.newcode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author caoduanxi
 * @Date 2020/2/10 9:26
 * 牛客网：滑动窗口的最大值
 * <p>
 * 题目：
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
 * 那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}，
 * {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}，
 * {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 */
public class MaxInWindows {
    /**
     * 第一种方法：时间复杂度O(n*w) w表示窗口个数
     * 空间复杂度O(1)
     */
    // 只要输出滑动窗口的最大值即可 时间复杂度O(n*size) size是窗口大小
    public ArrayList<Integer> maxInWindows1(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (num == null || size < 1 || num.length < size) return res;
        // 确保窗口大小为r-l=size
        int l = 0;
        int r = 0;
        // 用来记录当前窗口中的最大值
        int maxNum = Integer.MIN_VALUE;
        while (l <= r && r < num.length) {
            // 每次l处出去数，判断是否是最大的，是的话最大值变为最小，r回退
            maxNum = Math.max(maxNum, num[r]);
            if (r - l + 1 == size) {
                // 构建一个窗口，此时存入最大值
                res.add(maxNum);
                // 这一步主要是判断l端出去的值是否是当前最大值
                // 如果从l端出去的值是当前最大值，则最大值置为最初的模样
                if (maxNum == num[l++]) {
                    maxNum = Integer.MIN_VALUE;
                    // r回退，为什么是l-1，因为下面共用到r++
                    // 这样可以保证r与l在下一个窗口时重新开始，r重新扫描
                    r = l - 1;
                }
            }
            // 继续前进
            r++;
        }
        return res;
    }

    /**
     * 第二种方法：使用双端队列，存储最大值的下标
     * <p>
     * 这里比较难理解的地方在于：
     * 对于这个双端队列，从尾部插入的元素不一定是最大的，但是可以保证从头部
     * 吐出来的元素一定是最大的。
     * 能够保证的原因在于代码中的一旦遇到更大的会将之前的全部移除
     * 而比自己小的却可以附加在自己的后面，一旦自己作为最大的在窗口之外，也会被移除
     * <p>
     * 时间复杂度：O(n)  空间复杂度：O(n)
     */
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (num == null || size < 1 || num.length < size) return res;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            // 在未出现之前只保留最大值的下标
            while (!list.isEmpty() && num[i] > num[list.peekLast()]) {
                // 发现比自己最后插入要大的,则移除当前最后的元素
                list.removeLast();
            }
            // 如果没发现或者移除比当前元素小的元素之后，添加
            list.addLast(i);
            // 判断当前的下标是否有效,i-size是窗口大小
            // 打个比方：3-6 窗口长度为3  此时i=6  为了保证窗口3，移除3，则剩下4 5 6正好三个
            if (list.peekFirst() == i - size) {
                list.pollFirst();
            }
            // 只要窗口一旦滑动，过了第一个窗口即可存值
            if (i >= size - 1) {
                res.add(num[list.peekFirst()]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // 假如最大的要离开窗口，怎么解决
        MaxInWindows test = new MaxInWindows();
        ArrayList<Integer> integers = test.maxInWindows(new int[]{2, 3, 4, 2, 6, 2, 5, 1}, 3);
        ArrayList<Integer> integers1 = test.maxInWindows(new int[]{16, 14, 12, 10, 8, 6, 4}, 5);
        System.out.println(integers);
    }
}
