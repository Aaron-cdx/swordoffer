package com.duanxi.interview.Alibaba.one;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author caoduanxi
 * @Date 2020/8/17 18:58
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        List<List<Integer>> list = new ArrayList<>();
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        if(n < 1 || k < 1){
            System.out.println(0);
            return;
        }
        List<Integer> temp = null;
        for (int i = 0; i < n; i++) {
            temp = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                temp.add(scanner.nextInt());
            }
            list.add(temp);
        }
//        System.out.println(list);
        int cnt = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size() ; j++) {
                if(main.getSumEqual(list.get(i),list.get(j))){
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
    public boolean getSumEqual(List<Integer> l1, List<Integer> l2){
        int sum = l1.get(0)+l2.get(0);
        for (int i = 1; i < l1.size(); i++) {
            if(sum != (l1.get(i)+l2.get(i))){
                return false;
            }
        }
        return true;
    }
}
