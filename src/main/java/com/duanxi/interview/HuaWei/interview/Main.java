package com.duanxi.interview.HuaWei.interview;

import java.util.*;

/**
 * @Author: caoduanxi
 * @Date: 2020/9/8
 * 华为面试编程题
 * <p>
 * 题目：
 * n个数，1 2 3 4 5，求k个数的所有组合
 * 1 2  1 3  1 4  1 5
 * 2 3
 */
public class Main {
    private List<List<Integer>> lists = new ArrayList<>();

    public static void main(String[] args) {
        Main main = new Main();
        int[] nums = new int[]{1,2,3,4,5};
        main.getAll(nums,3,new ArrayList<Integer>(),0);
        System.out.println(main.lists);
        System.out.println(main.lists.size());
    }

    public void getAll(int[] nums, int k, List<Integer> list, int index) {
        if (list.size() == k) {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            getAll(nums, k, list, i + 1);
            list.remove(list.size() - 1);
        }
    }


}
