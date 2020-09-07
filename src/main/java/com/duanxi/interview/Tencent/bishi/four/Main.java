package com.duanxi.interview.Tencent.bishi.four;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * n个数，n为偶数，求出删除当前数之后的中位数是多少？
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        int n = scanner.nextInt();
        List<Integer> middle = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(scanner.nextInt());
        }
        for (int i = 0; i < n; i++) {
            int temp = res.remove(i);
            middle.add(main.getMid(new ArrayList<>(res)));
            res.add(i, temp);
        }
        for (int integer : middle) {
            System.out.println(integer);
        }
    }
    /**
     * 需要将剩下的元素排个序，然后再输出
     */
    public int getMid(List<Integer> list){
        Collections.sort(list);
        return list.get(list.size()/2);
    }
}
