package com.duanxi.interview.Aiqiyi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/8/23 16:02
 */
public class Test {
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1,1));
        List<Integer> list1 = Arrays.asList(1,1);
        System.out.println(list.contains(list1));
    }
}
class A{
    int x;
    int y;

    public A(int x, int y) {
        this.x = x;
        this.y = y;
    }
}