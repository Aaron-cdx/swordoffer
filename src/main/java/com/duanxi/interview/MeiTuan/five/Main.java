package com.duanxi.interview.MeiTuan.five;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/8/15 17:35
 * 全排列题
 */
public class Main {
    private static List<List<Integer>> list = new ArrayList<>();
    private static long cnt = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        main.getNums(n,m,new ArrayList<>());
//        System.out.println(list);
        System.out.println(cnt % 998244353);
    }

    public void getNums(int n, int m, List<Integer> tempList) {
        if (tempList.size() == m) {
//            list.add(new ArrayList<>(tempList));
            cnt += 1;
//            list.clear();;
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (tempList.size() != 0) {
                int temp = tempList.get(tempList.size() - 1);
                if (i >= temp && i % temp == 0) {
                    tempList.add(i);
                    getNums(n, m, tempList);
                    tempList.remove(tempList.size()-1);
                }
            }else{
                tempList.add(i);
                getNums(n, m, tempList);
                tempList.remove(tempList.size()-1);
            }
        }
    }
}
