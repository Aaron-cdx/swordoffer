package com.duanxi.interview.MeiTuan.two;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/8/15 16:48
 * 旅游路线问题闭环
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        List<String> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String string = scanner.nextLine();
            String[] s1 = string.split(" ");
            list.add(s1[0]);
            list.add(s1[1]);
        }
        System.out.println(main.getNums(list));
    }

    public int getNums(List<String> list) {
        Stack<String> stack = new Stack<>();
        int cnt = 0;
        for (String s : list) {
            if (cnt == 0 && stack.isEmpty()) {
                stack.push(s);
            } else if (!stack.isEmpty() && stack.peek().equals(s)) {
                stack.pop();
                if (stack.isEmpty()) {
                    cnt++;
                }
            } else {
                stack.push(s);
            }
        }
        return cnt;
    }
}
