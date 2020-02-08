package com.duanxi.newcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/2/8 10:59
 * 牛客网：字符流中第一个不重复的字符
 * <p>
 * 题目：
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * <p>
 * 输出描述：
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
 */
public class NoRepeatInStringStream {
    /**
     * 第一种方法：
     * 可以使用LinkedList的先进先出，每次第一个不相同的都在首部
     * 只要遇到相同的就删除
     * <p>
     * 第二种方法：利用Map
     * 每次插入求次数，每次获取遍历所有的值，取出为1次的值即可
     * <p>
     * 第三种方法：使用数组方法，利用字符串拼接
     * 字符串拼接，然后用数组记录，只要命中就加一，每次取出遍历n次获取为1
     * 的字符，没有返回'#'
     */
    /*// 采用先进先出，使用队列
    private LinkedList<Character> list = new LinkedList<>();

    //Insert one char from stringstream
    public void Insert(char ch) {
        if (!list.contains(ch)) {
            list.add(ch);
        } else {
            // 移除第一次出现的当前元素
            list.removeFirstOccurrence(ch);
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        if (list.size() == 0) {
            return '#';
        }
        return list.peekFirst();
    }*/

    /**
     * 注意如果全局变量的String不赋初始值的话默认为"null"拼接为"nullh"
     * h为拼接的字母
     */
    private String s = "";
    private int[] tab = new int[256];

    //Insert one char from stringstream
    public void Insert(char ch) {
        s += ch;
        tab[ch]++;
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        // 每一次获取的话
        int size = s.length();
        for (int i = 0; i < size; i++) {
            if (tab[s.charAt(i)] == 1) {
                return s.charAt(i);
            }
        }
        return '#';
    }

    public static void main(String[] args) {
        NoRepeatInStringStream test = new NoRepeatInStringStream();
        test.Insert('h');
        System.out.print(test.FirstAppearingOnce());
        test.Insert('e');
        System.out.print(test.FirstAppearingOnce());
        test.Insert('l');
        System.out.print(test.FirstAppearingOnce());
        test.Insert('l');
        System.out.print(test.FirstAppearingOnce());
        test.Insert('o');
        System.out.print(test.FirstAppearingOnce());
        test.Insert('e');
        System.out.print(test.FirstAppearingOnce());
    }
}
