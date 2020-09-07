package com.duanxi.interview.Tencent.bishi.two;

/**
 * @Author: caoduanxi
 * @Date: 2020/9/7
 */

import java.util.*;

/**
 * 通知传递，n个人（序号0~n），m个小组，每个小组有k个人，1个人也能在不同的小组。
 * 由0发起的一个通知，每个人都能把通知告诉自己同组的人，求最后收到通知的人数。
 * <p>
 * 思路：
 * 用一个HashMap<Integer,Set<Integer>> map保存下每个人能够影响的范围，然后遍历图即可
 * 这是在遍历的时候对每一个数都做了一个集合操作，只要涉及到当前数的就将含有当前数的集合放入即可
 */
/*
    测试数据
    50 5
    2 1 2
    5 10 11 12 13 14
    2 0 1
    2 49 2
    4 6 7 8 2

    最后结果7  =>  0 1 2 6 7 8 49
     */
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int k = scanner.nextInt();
            Set<Integer> set = new HashSet<>();
            // 在处理过程中遍历所有的元素
            // 这是当前这个分组中所有的元素
            for (int j = 0; j < k; j++) {
                set.add(scanner.nextInt());
            }
            // 建立一个图，即获取当前元素的一个set集合
            for (Integer integer : set) {
                Set<Integer> set1 = map.get(integer);
                if (set1 == null || set1.isEmpty()) {
                    map.put(integer, set);
                    continue;
                }
                // 如果当前已经有的话再添加一次
                set1.addAll(set);
                map.put(integer, set1);
            }
        }
        System.out.println(map);
        // 现在需要遍历所有的然后加入即可
        Queue<Integer> queue = new LinkedList<>(map.get(0));
        // 使用一个set来存储结果
        Set<Integer> result = new HashSet<>();
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            Set<Integer> integers = map.get(poll);
            // 结果集中不包含的才可以放入，否则排除
            for (Integer integer : integers) {
                if (!result.contains(integer)) {
                    queue.add(integer);
                    result.add(integer);
                }
            }
        }
        System.out.println(result);
        System.out.println(result.size());
    }
}
