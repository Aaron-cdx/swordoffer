package com.duanxi.interview.Tencent.bishi.one;

import java.util.HashMap;
import java.util.*;
import java.util.Scanner;

/**
 * 降序排列的链表，求出当前链表的公共部分
 * 6
 * 6 5 4 3 2 1
 * 5
 * 6 5 3 2 1
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n =scanner.nextInt();
        List<Integer> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(scanner.nextInt());
        }
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            int tmp = scanner.nextInt();
            if(!set.add(tmp)){
                res.add(tmp);
            }
        }
        for (Integer re : res) {
            System.out.print(re+" ");
        }
    }
}
