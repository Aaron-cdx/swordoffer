package com.duanxi.interview.Tencent.bishi.three;

import java.util.*;
import java.util.Scanner;

/**
 * 字典序比较出现次数前k的字符串和出现次数后k的字符串
 * <p>
 * 输入N个字符串（有重复），输出这N个字符串中出现次数最多的前K个及出现次数最少的前K个和他们的出现的次数，
 * 注意出现次数一样的时候都要进行字典排序
 * <p>
 * 输出出现次数最多的前k个和出现次数最小的前k个
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();// 出现次数最多的k个，和出现次数最小的k个
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String val = scanner.next();
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        List<Word> list = new ArrayList<>();
        for (String s : map.keySet()) {
            list.add(new Word(s, map.get(s)));
        }
        // 重写比较器
        list.sort((o1, o2) -> {
            // 先比较出现次较多
            if (o1.rank < o2.rank) return 1; // 默认是小的排在前面，但是如果想让大的排在前面的话，需要使用小于则返回1
            if (o1.rank > o2.rank) return -1;
            return o1.str.compareTo(o2.str);
        });
        // 输出后k个
        for (int i = 0; i < k; i++) {
            Word word = list.get(i);
            System.out.println(word.str + " " + word.rank);
        }
        list.sort((o1, o2) -> {
            // 先比较出现次比较少的两个
            if (o1.rank > o2.rank) return 1;
            if (o1.rank < o2.rank) return -1;
            return o1.str.compareTo(o2.str);
        });
        for (int i = 0; i < k; i++) {
            Word word = list.get(i);
            System.out.println(word.str + " " + word.rank);
        }
    }
}

class Word {
    String str;
    int rank;

    public Word(String str, int rank) {
        this.str = str;
        this.rank = rank;
    }
}
