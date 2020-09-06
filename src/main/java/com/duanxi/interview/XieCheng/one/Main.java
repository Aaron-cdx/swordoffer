package com.duanxi.interview.XieCheng.one;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    private static Set<Integer> set = new HashSet<>();

    static int[] divingBoard(int a, int b, int k) {
        // 可以使用递归的方法来获取值
        getAll(a, b, k, k, 0);
        // 主要在这里实现主要的逻辑代码
        // 进行全排列就好了
        List<Integer> list = new ArrayList<>(set);
        if (list.size() == 0) return null;
        Collections.sort(list);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private static void getAll(int a, int b, int k, int aNum, int bNum) {
        if (aNum < 0 || bNum > k) return;
        set.add(a * aNum + b * bNum);
        getAll(a, b, k, aNum - 1, bNum + 1);
    }
//    private static Set<Integer> set = new HashSet<>();
//    static int[] divingBoard(int a, int b, int k) {
//        int[] AB = new int[2];
//        AB[0] = a;
//        AB[1] = b;
//        getAllPaiLie(AB,k,new ArrayList<>());
//        // 主要在这里实现主要的逻辑代码
//        // 进行全排列就好了
//        List<Integer> list = new ArrayList<>(set);
//        if(list.size() == 0) return null;
//        Collections.sort(list);
//        int[] res = new int[list.size()];
//        for (int i = 0; i < list.size(); i++) {
//            res[i] = list.get(i);
//        }
//        return res;
//    }

//    private static void getAllPaiLie(int[] AB,int k,List<Integer> list){
//        if(list.size() == k){
//            int sum = 0;
//            for (Integer integer : list) {
//                sum += integer;
//            }
//            set.add(sum);
//            return;
//        }
//        for (int value : AB) {
//            list.add(value);
//            getAllPaiLie(AB, k, list);
//            list.remove(list.size() - 1);
//        }
//    }

    /******************************结束写代码******************************/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] res;

        int _a;
        _a = Integer.parseInt(in.nextLine().trim());

        int _b;
        _b = Integer.parseInt(in.nextLine().trim());

        int _k;
        _k = Integer.parseInt(in.nextLine().trim());

        res = divingBoard(_a, _b, _k);
        String value = "[]";
        if (res != null && res.length > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < res.length; i++) {
                if (i == 0) {
                    stringBuilder.append("[");
                }
                stringBuilder.append(res[i]);
                if (i == res.length - 1) {
                    stringBuilder.append("]");
                } else {
                    stringBuilder.append(",");
                }
            }
            value = stringBuilder.toString();
        }
        System.out.println(value);
    }
}