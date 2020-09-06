package com.duanxi.interview.Bilibili.chenxiang.one;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/9/4 19:39
 * 输入一个小于100的正整数n，输出一个正整数m，使得m的各位平方之和等于n
 */
public class Main {

    public static int cnt = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        int n = scanner.nextInt();
        main.getNum(n, new ArrayList<>());
        System.out.println(cnt);
    }

    private void getNum(int n, List<Integer> list) {
        if (n == 0) {
            cnt = Math.min(cnt, getNum(list));
            return;
        }
        for (int i = 9; i >= 1; i--) {
            if (n - i * i < 0 || (n - (i * i)) > (i * i)) {
                continue;
            }
            list.add(i);
            getNum(n - i * i, list);
            list.remove(list.size() - 1);
        }
    }

    public int getNum(List<Integer> list) {
        StringBuilder sum = new StringBuilder();
        for (Integer num : list) {
            sum.append(num);
        }
        return Integer.parseInt(sum.reverse().toString());
    }
}
