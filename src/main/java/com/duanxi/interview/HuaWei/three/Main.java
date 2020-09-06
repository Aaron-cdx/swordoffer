package com.duanxi.interview.HuaWei.three;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author caoduanxi
 * @Date 2020/8/12 20:23
 * // 这里需要给数字排列即可
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int n = scanner.nextInt();
        int len = str.length();
        char[][] chars = new char[n][len];
        // 3/2 = 1 => 0 1
        // 5/2 = 2 => 0 1 2
        int index = 0;

        // 填充空字符
        for (int i = 0; i < chars.length; i++) {
            Arrays.fill(chars[i],' ');
        }
        for (int i = 0; i < len; i++) {
            // 第0行的时候

        }
    }
}
