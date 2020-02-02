package com.duanxi.newcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/2/2 10:34
 * 牛客网：把数组排成最小的数
 * <p>
 * 题目：
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 */
public class PrintMinNumber {
    /**
     * 解题思路：
     * 主要是围绕着所有的结果都是组合来定的，所以需要对数组中的整数
     * 做一个排序，排序围绕着组合的结果来排序
     * 前后组合排序，谁小谁排在前面。主要看比较器的代码怎么写！
     */
    public String PrintMinNumber(int[] numbers) {
        // 所有的代码到最后都是要通过组合来获取最终的值
        List<String> list = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            list.add(numbers[i] + "");
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1 + "" + o2;
                String s2 = o2 + "" + o1;
//                return s1.compareTo(s2);
                return s2.compareTo(s1);
            }
        });
        StringBuilder s = new StringBuilder("");
        for (String s1 : list) {
            s.append(s1);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        PrintMinNumber test = new PrintMinNumber();
        String s = test.PrintMinNumber(new int[]{3, 32, 321});
        System.out.println(s);
    }
}
