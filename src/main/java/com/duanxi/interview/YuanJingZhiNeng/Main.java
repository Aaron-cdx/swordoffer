package com.duanxi.interview.YuanJingZhiNeng;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/7/22 17:43
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int winner = main.getWinner(5, 3);
        System.out.println(winner);
    }

    public int getWinner(int n, int m){
        // n个人，每次报数到m的时候，将这个人杀掉，直到最后一个人，输出最后一个人的编号
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        // 5个人的话，此时就是1 2 3 4 5
        // 假如m是3
        int index = 0;// 定义当前位置变量
        while (list.size() > 1) {
            // 一定是循环的去找这个值
            index = (index + m) % list.size();
            list.remove(index);
    }
        return list.get(0);
    }
}
