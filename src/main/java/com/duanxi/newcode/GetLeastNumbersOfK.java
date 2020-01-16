package com.duanxi.newcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @author caoduanxi
 * @Date 2020/1/12 21:41
 * 获取最小的k个数
 * <p>
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */
public class GetLeastNumbersOfK {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if(k > input.length || k <= 0) return list;
        // 使用优先队列
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        // 维持这个大小在4即可
        for (int num : input) {
            if (queue.size() == k) {
                if (num < queue.peek()){
                  queue.poll();
                  queue.add(num);
                }
            }else{
                queue.add(num);
            }
        }
        return new ArrayList<>(queue);
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(4, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        queue.add(3);
        queue.add(2);
        queue.add(1);
        queue.add(5);
//        System.out.println(queue.);
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
