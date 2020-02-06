package com.duanxi.newcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author caoduanxi
 * @Date 2020/2/6 11:20
 * 牛客网：和为S的两个数字
 * <p>
 * 题目：
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，
 * 使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 * <p>
 * 输出描述：
 * 对应每个测试案例，输出两个数，小的先输出。
 */
public class FindNumbersWithSum {
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> res = new ArrayList<>();
        /**
         * 第一种利用数据结构中的map结构
         * 方法借助数据结构，不太好
         */
        /*// 因为和为S的两个数，乘积最小的可能有多个。所以使用list存储
        // 使用hashMap存储
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : array) {
            if (sum > num) {
                map.put(sum - num, num);
            }
        }
        int multiRes = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                int curMulti = array[i] * map.get(array[i]);
                // 获取到乘积之后，判断是否需要更新list存储的数据
                if (multiRes > curMulti) {
                    res.clear();
                    multiRes = curMulti;
                    // 添加元素至res中，array[i]一定是最小的，因为map存储数据的原因
                    res.add(array[i]);
                    res.add(sum - array[i]);
                    // 去除重复取值
                    map.remove(array[i]);
                }
            }
        }*/
        /**
         * 第二种方法：利用左右夹逼
         *
         * 这是最简便的，乘积最小的就是两边，一旦第一次遇到可以保证是最小的
         * 越往中间越大
         */
        int l = 0;
        int r = array.length - 1;
        while (l < r) {
            int s = array[l] + array[r];
            if (s == sum) {
                // 两者相等
                res.add(array[l]);
                res.add(array[r]);
                return res;
            } else if (s < sum) {
                l++;
            } else {
                r--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindNumbersWithSum test = new FindNumbersWithSum();
        ArrayList<Integer> integers = test.FindNumbersWithSum(new int[]{1, 2, 4, 7, 11, 15}, 15);
        ArrayList<Integer> res = test.FindNumbersWithSum(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20}, 21);
        System.out.println(integers);
        System.out.println(res);
    }
}
