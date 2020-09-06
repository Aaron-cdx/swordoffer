package com.duanxi.interview.Aiqiyi.two;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author caoduanxi
 * @Date 2020/8/23 15:29
 * 从(0,0)出发，每次可以向上下左右各走一步判断当的
 */
public class Main {
    private static List<List<Integer>> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        list.add(Arrays.asList(0,0));
        String s = scanner.nextLine();
        boolean repeat = main.isRepeat(s, 0, 0, 0);
        System.out.println(repeat ? "True" : "False");
    }

    public boolean isRepeat(String str, int index, int x, int y) {
        if (index == str.length()) return false;
        int[] move = getMove(str.charAt(index));
        int n_x = x + move[0];
        int n_y = y + move[1];
        List<Integer> tmpList = Arrays.asList(n_x,n_y);
        if (list.contains(tmpList)) {
            return true;
        }else{
            list.add(tmpList);
            return isRepeat(str, index + 1, n_x, n_y);
        }
    }

    public int[] getMove(char c) {
        if (c == 'N') {
            return new int[]{0, 1};
        } else if (c == 'S') {
            return new int[]{0, -1};
        } else if (c == 'E') {
            return new int[]{1, 0};
        } else {
            return new int[]{-1, 0};
        }
    }
}

class Axis {
    int x;
    int y;

    public Axis(int x, int y) {
        this.x = x;
        this.y = y;
    }


}
