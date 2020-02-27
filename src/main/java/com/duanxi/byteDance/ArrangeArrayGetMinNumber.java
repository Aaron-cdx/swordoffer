package com.duanxi.byteDance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/2/27 20:45
 * 字节跳动：将数组中的数字排成最小的数
 * <p>
 * 题目：
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 */
public class ArrangeArrayGetMinNumber {

    public String getMinNumber(int[] nums) {
        if (nums == null) return "";
        StringBuilder res = new StringBuilder();
        // 先转换为list，String类型的
        List<String> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num + "");
        }
        // 然后通过list中重写比较方法，将所有的数据从小到大(组合的大小)依次排序，最后直接拼接即可
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1 + o2;
                String s2 = o2 + o1;
                return s1.compareTo(s2);
            }
        });
        for (String s : list) {
            res.append(s);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        ArrangeArrayGetMinNumber test = new ArrangeArrayGetMinNumber();
        System.out.println(test.getMinNumber(new int[]{3, 32, 321}));
    }
}
