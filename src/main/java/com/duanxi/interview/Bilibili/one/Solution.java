package com.duanxi.interview.Bilibili.one;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出四个数，做任意的加减乘除，判断是否能够为24，
 * 如果可以则true,否则返回false
 * <p>
 * 首先对这四个数进行全排列，则总共有24种组合
 * 然后对这个24种组合进行一个赋值运算符操作，总共有4x4x4=64中排列
 * 总共需要24*64次计算
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.Game24Points(new int[]{7, 2, 1, 10}));
    }

    /**
     * @param arr int整型一维数组
     * @return bool布尔型
     */
    List<List<Integer>> res = new ArrayList<>();

    public boolean Game24Points(int[] arr) {
        // write code here
        boolean[] visited = new boolean[arr.length];
        getAll(arr, new ArrayList<>(), visited);
        // res有所有的可能性
        for (List<Integer> re : res) {
            if (pointsEqual24(re)) {
                return true;
            }
        }
        return false;
    }

    public void getAll(int[] arr, List<Integer> list, boolean[] visited) {
        if (list.size() == arr.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) continue;
            list.add(arr[i]);
            visited[i] = true;
            getAll(arr, list, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }

    public boolean pointsEqual24(List<Integer> list) {
        char[] chars = new char[]{'+', '-', '*', '/'};
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            res = getRes(list.get(0), chars[i], list.get(1));
            for (int j = 0; j < chars.length; j++) {
                res = getRes(res, chars[j], list.get(2));
                for (int k = 0; k < chars.length; k++) {
                    res = getRes(res, chars[k], list.get(3));
                    if (res == 24) return true;
                }
            }
        }
        return false;
    }

    public int getRes(int x, char c, int y) {
        if (c == '*') {
            return x * y;
        } else if (c == '+') {
            return x + y;
        } else if (c == '-') {
            return x - y;
        } else {
            return x / y;
        }
    }
}