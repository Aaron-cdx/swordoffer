package com.duanxi.interview.Alibaba.two;

import java.util.Scanner;

/**
 * @author caoduanxi
 * @Date 2020/8/17 18:58
 */
public class Main {
    /**
     * 每个节点无非三种情况
     * 第一层占据一个节点
     * 然后判断高度，如果第二层有一个高度，此时如果高度正好，则所有节点必须全排
     * 否则可以一个在左边，然后继续递归
     * 然后一个在右边继续递归
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println(main.getAll(n - 1, m - 1, 1));
    }

    private int getAll(int n, int m, int depth) {
        if (n == 0 || depth > m + 1) return 0;
        int sum = 0;
        if (depth + 1 <= m + 1) {
            // 左右的情况，这是小于的情况
            sum += 2 + 2 * getAll(n - 1, m, depth + 1);
        } else {
            // 否则就是等于的情况
            sum += 1;
        }
        return sum;
    }
}
