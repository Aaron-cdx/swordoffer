package com.duanxi.interview.didi.one;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/8/21 18:51
 * 输入一个n，此时获取当前能够通过
 * abc+acc=n的对数，注意 a != b != c
 * 如果有多对，按照abc升序输出 // 即第一个数越小越在前面
 */
public class Main {
    private static List<List<Integer>> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        int n = scanner.nextInt();
        // 这里i如果是0，则71%,如果i是1的话直接100%
        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == i) continue;
                for (int k = 0; k <= 9; k++) {
                    if (k == i || k == j) continue;
                    main.getSum(n,i,j,k);
                }
            }
        }
        Collections.sort(list, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0) - o2.get(0);
            }
        });
        System.out.println(list.size());
        if(list.size() < 1){
            return;
        }
        for (List<Integer> integers : list) {
            System.out.println(integers.get(0)+" "+integers.get(1));
        }
    }

    public void getSum(int n, int a, int b, int c) {
        int num1 = a * 100 + b * 10 + c;
        int num2 = a * 100 + c * 10 + c;
        List<Integer> res = new ArrayList<>();
        if (n == (num1 + num2)) {
            res.add(num1);
            res.add(num2);
            list.add(res);
        }
    }
}
