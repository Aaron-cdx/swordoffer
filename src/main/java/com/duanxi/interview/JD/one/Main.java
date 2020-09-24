package com.duanxi.interview.JD.one;

import java.util.Scanner;

/**
 * @Author: caoduanxi
 * @Date: 2020/9/17
 * n行m列的二维地图，公主位置为x2 y2，王子位置为x1,y1
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        int t = scanner.nextInt();
        // 这里是数据有多少组
        for (int k = 0; k < t; k++) {
            int x1 = -1;
            int y1 = -1;
            int x2 = -1;
            int y2 = -1;
            // 这里获取输入的n行m列
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            // 构建字符串二维数组，行n列m
            String[][] map = new String[n][m];
            // 构建一个n行m列的布尔型数组，为了记录走过的地方，防止栈溢出
            boolean[][] visited = new boolean[n][m];
            // 开始通过遍历获取输入的n行m列数据
            for (int i = 0; i < n; i++) {
                String[] s = scanner.next().split("");
                for (int j = 0; j < m; j++) {
                    // 由于王子是为S的，所以这里如果遇到了王子，记录它所在的位置(x1,y1)
                    if (s[j].equals("S")) {
                        x1 = i;
                        y1 = j;
                    }
                    // 因为工作是为E的，所以这里如果遇到了公主，记录她所在的位置(x2,y2)
                    if (s[j].equals("E")) {
                        x2 = i;
                        y2 = j;
                    }
                    // 这里主要是将值存入到二维数组中
                    map[i][j] = s[j];
                }
            }
            // 这里判断是否找到，利用三目运算符，如果找到则是true=>输出YES，如果没找到则是false=>输出NO
            System.out.println(main.findXY(map, x1, y1, x2, y2, n, m, visited) ? "YES" : "NO");
        }

    }

    /**
     * 通过递归的方法判断王子是否可以走到公主那里
     */
    public boolean findXY(String[][] map, int x1, int y1, int x2, int y2, int rows, int cols, boolean[][] visited) {
        // 首先将结果设置为false，即假设找不到
        boolean res = false;
        // 这里是判断边界条件，防止数组越界;同时需要判断当前位置是否走过，走过的话忽略; 且由于存在陷阱#，所以遇到#号也不走
        if (x1 >= 0 && x1 < rows && y1 >= 0 && y1 < cols && !visited[x1][y1] && !map[x1][y1].equals("#")) {
            // 这里表示王子找到了公主，直接返回true，即真(通过判断横纵坐标值)
            if (x1 == x2 && y1 == y2) {
                return true;
            }
            // 表示这里王子走过
            visited[x1][y1] = true;
            // 依次通过上下左右去挨个尝试
            // 左：x1-1 右: x1+1 上: y1+1  下: y1-1  (这就是我们平时的坐标轴)
            // 因为是或运算，只要一个为真即表示为真
            res = findXY(map, x1 + 1, y1, x2, y2, rows, cols, visited)
                    || findXY(map, x1 - 1, y1, x2, y2, rows, cols, visited)
                    || findXY(map, x1, y1 - 1, x2, y2, rows, cols, visited)
                    || findXY(map, x1, y1 + 1, x2, y2, rows, cols, visited);
            // 本次走完，下次走需要释放位置，即改为false
            visited[x1][y1] = false;
        }
        // 最后将结果返回
        return res;
    }
}
