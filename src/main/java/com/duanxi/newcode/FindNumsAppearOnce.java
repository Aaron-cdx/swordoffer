package com.duanxi.newcode;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2020/2/5 14:07
 * 牛客网：数组中只出现一次的数字
 * <p>
 * 题目：
 * 一个整型数组里除了两个数字之外，
 * 其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 */
public class FindNumsAppearOnce {
    // num1,num2分别为长度为1的数组。传出参数
    // 将num1[0],num2[0]设置为返回结果
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        //
        int n = array.length;
        if (n == 2) {
            num1[0] = array[0];
            num2[0] = array[1];
            return;
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            result ^= array[i];
        }
        int index = findFirstBitOfOne(result);
        for (int i = 0; i < n; i++) {
            // 这个地方总能够命中一次！
            if (isBit(array[i], index)) {
                num1[0] ^= array[i];
            } else {
                // 只要剩余的数字一直异或，一定能找到另外一个元素
                num2[0] ^= array[i];
            }
        }
    }

    /**
     * 找出所在的数组中那两个出现单次的数字
     */
    private boolean isBit(int result, int index) {
        return (result >> index & 1) == 1;
    }

    /**
     * 找出第一个1所在的下标的索引位置index大小
     */
    private int findFirstBitOfOne(int result) {
        int index = 0;
        // 一旦不等于0就找到了第一位index
        while ((result & 1) == 0 && index < 32) {
            result >>= 1;
            index++;
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println(1 ^ 2 ^ 2);
    }
}
