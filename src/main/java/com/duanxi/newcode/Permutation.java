package com.duanxi.newcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author caoduanxi
 * @Date 2020/1/10 18:35
 * 牛客网：字符串的排列
 * <p>
 * 题目：
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,
 * 则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 */
public class Permutation {
    ArrayList<String> res = new ArrayList<>();

    public ArrayList<String> Permutation(String str) {
        if (str == null || str.length() == 0) return res;
        // abc acb bac bca cab cba
        // 每个节点都作为首字母，然后再处理
        execute(str.toCharArray(), 0);
        Collections.sort(res);
        return res;
    }

    private void execute(char[] chars, int index) {
        if (index == chars.length - 1) {
            res.add(String.valueOf(chars));
        }
        Set<Character> charSet = new HashSet<>();
        for (int i = index; i < chars.length; i++) {
            if (i == index || !charSet.contains(chars[i])) {
                charSet.add(chars[i]);
                swap(chars, i, index);
                execute(chars, index + 1);
                swap(chars, index, i);
            }
        }
    }

    public void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        Permutation pre = new Permutation();
        ArrayList<String> abc = pre.Permutation("abc");

    }
}
