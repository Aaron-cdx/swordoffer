package com.duanxi.interview.Tencent.bishi.three;

import java.nio.channels.Pipe;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: caoduanxi
 * @Date: 2020/9/7
 */
public class Compare {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) return -1;
                if (o1 < o2) return 1;
                return 0;
            }
        });
        queue.add(1);
        queue.add(5);
        queue.add(4);
        queue.add(8);
        for (Integer integer : queue) {
            System.out.println(integer);
        }
    }
}
