package com.duanxi.interview.Tencent.bishi.two;


import java.util.*;

/**
 * 找出公共的集合，只要是存在与0元素在一个团队的，这个团队中所有的人都需要被纳入到接收到信号的人群
 * 并查集的知识！！只可惜自己不会！！
 */
public class Main {
    /*
    50 5
    2 1 2
    5 10 11 12 13 14
    2 0 1
    2 49 2
    4 6 7 8 2

    最后结果7  =>  0 1 2 6 7 8 49
     */
    private static Set<Integer> set = new HashSet<>();
    static int[] arr;

    /**
     * 暴力解法
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        List<List<Integer>> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int start = 0;
        // 以这个作为法院
        for (int i = 0; i < n; i++) {
            List<Integer> tempList = new ArrayList<>();
            int x = scanner.nextInt();
            for (int j = 0; j < x; j++) {
                int ans = scanner.nextInt();
                tempList.add(ans);
                if (ans == 0) {
                    start = i;
                }
            }
            list.add(tempList);
        }
        arr = new int[list.size()];
        visit(list, start);
        System.out.println(set.size());
    }

    private static void visit(List<List<Integer>> lists, int start) {
        List<Integer> list = lists.get(start);
        // 将他们放入到当前的set中去
        set.addAll(list);
        arr[start] = 1;
        for (Integer num : list) {
            for (int i = 0; i < lists.size(); i++) {
                List<Integer> list1 = lists.get(i);
                if (list1.contains(num) && arr[i] == 0) {
                    visit(lists, i);
                }
            }
        }
    }
}
