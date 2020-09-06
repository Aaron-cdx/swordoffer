package com.duanxi.interview.SHEIN;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

/**
 * @author caoduanxi
 * @Date 2020/7/21 22:45
 * SHEIN:
 * 对各个英文字符大小写分开进行统计，还包括数字、空格等
 * 按照统计个数从大到小排列，如果个数相同，则按照ASCII码由大到小排列
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(main.alphabetSort(str));
    }

    public String alphabetSort(String str){
        if(str.trim().length() == 0) return "";
        int[] chars = new int[256];
        char[] array = str.toCharArray();
        for (char c : array) {
            chars[c]++;
        }
        StringBuilder sb = new StringBuilder();
        int index = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < 256; j++) {
                if(chars[j] == 0) continue;
                if(chars[j] > max){
                    max = chars[j];
                    index = j;
                }
            }
            sb.append((char)index);
            chars[index] = 0;
            index = 0;
            max = 0;
        }
        return sb.toString();
    }
}
