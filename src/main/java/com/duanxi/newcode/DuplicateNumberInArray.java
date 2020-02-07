package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/7 12:34
 * 牛客网：数组中重复的数字
 * <p>
 * 题目：
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 */
public class DuplicateNumberInArray {
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        // 只需要判断是否重复就好，将重复的元素放在duplicatin[0]中去即可
        // 长度大的话一定返回true
        /**
         * 第一种方法：剑指offer上面的方法
         */
        /*if(numbers == null) return false;
        if (numbers.length > length) return true;
        for (int i = 0; i < length; i++) {
            // 与自己位置不相等
            while (i != numbers[i]) {
                if (numbers[i] == numbers[numbers[i]]) {
                    duplication[0] = numbers[i];
                    return true;
                }
                // 不等的时候执行交换
                swap(numbers, i, numbers[i]);
            }
        }
        return false;*/
        /**
         * 第二种方法：参考网上的方法
         * 经过当前数的时候，+len 超过len的话表示经历过了
         *
         * 这个即使经过就加，再次经过就减少，最后如果经过的
         * 同一个值还是大，则是重复
         */
        if (numbers == null) return false;
        for (int i = 0; i < length; i++) {
            int index = numbers[i];
            if (index >= length) {
                index -= length;
            }
            if (numbers[index] >= length) {
                duplication[0] = index;
                return true;
            }
            numbers[index] = numbers[index] + length;
        }
        return false;
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        DuplicateNumberInArray test = new DuplicateNumberInArray();
        boolean duplicate = test.duplicate(new int[]{2, 3, 1, 0, 2, 5, 3}, 7, new int[1]);
        System.out.println(duplicate);
    }
}
