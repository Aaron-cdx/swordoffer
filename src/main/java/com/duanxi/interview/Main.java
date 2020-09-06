package com.duanxi.interview;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author caoduanxi
 * @Date 2020/3/19 20:00
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        PriorityQueue<Integer> queue1 = new PriorityQueue<>();
        PriorityQueue<Integer> queue2 = new PriorityQueue<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            if (queue1.size() == 3) {
                queue1.poll();
            }
            queue1.offer(scanner.nextInt());
        }
        for (int i = 0; i < n; i++) {
            if (queue2.size() == 3) {
                queue2.poll();
            }
            queue2.offer(scanner.nextInt());
        }
        int max = main.getMax(queue1, queue2);
        System.out.println(max);
    }

    public int getMax(PriorityQueue<Integer> queue1, PriorityQueue<Integer> queue2) {
        int sum1 = 0;
        while (!queue1.isEmpty()) {
            sum1 += queue1.poll();
        }
        int sum2 = 0;
        while (!queue2.isEmpty()) {
            sum2 += queue2.poll();
        }
        return sum1 > sum2 ? sum1 : sum2;
    }


}
