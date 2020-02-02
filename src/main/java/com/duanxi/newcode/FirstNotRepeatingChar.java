package com.duanxi.newcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caoduanxi
 * @Date 2020/2/2 12:19
 * 牛客网：第一个只出现一次的字符
 * <p>
 * 题目：
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
 * 并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 */
public class FirstNotRepeatingChar {
    public int FirstNotRepeatingChar(String str) {
        if (str.length() == 0 || str == null) return -1;
        // 第一种方法，使用map键值对
        /*Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            map.put(str.charAt(i),map.getOrDefault(str.charAt(i),0)+1);
        }
        for (int i = 0; i < str.length(); i++) {
            if(map.get(str.charAt(i)) == 1){
                return i;
            }
        }*/
        // 使用数组,建议使用数组的方式
        int[] res = new int['z'];
        for (int i = 0; i < str.length(); i++) {
            res[str.charAt(i) - 'A']++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (res[str.charAt(i) - 'A'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FirstNotRepeatingChar test = new FirstNotRepeatingChar();
        System.out.println(test.FirstNotRepeatingChar("google"));
        System.out.println(test.FirstNotRepeatingChar("NXWtnzyoHoBhUJaPauJaAitLWNMlkKwDYbbigdMMaYfkVPhGZcrEwp"));
    }
}
