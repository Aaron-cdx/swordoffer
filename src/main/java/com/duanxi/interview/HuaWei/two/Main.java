package com.duanxi.interview.HuaWei.two;

import java.util.Scanner;

/**
 * @author caoduanxi
 * @Date 2020/8/12 19:53
 * S 步长
 * M*N 表示坐标系大小，保证左上角和右下角位置为1
 * m行表示构建数据
 * n列表示构建数组
 * 0的位置表示没有地砖
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        int S = scanner.nextInt();
        int M = scanner.nextInt();
        int N = scanner.nextInt();
        int[][] arr = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
        boolean[][] visited = new boolean[M][N];
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[0].length; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }
        int res = main.isOk(S,0,N,0,M,arr,visited) ? 1 : 0;
        System.out.println(res);
    }

    // 其实说到底只有两个方向可以走，向右向下
    // 可以向上走
    public boolean isOk(int step, int col, int cols, int row, int rows, int[][] arr, boolean[][] visited) {
        if (col == cols - 1 && row == rows - 1) return true;
        boolean res = false;
        if (col >= 0 && col < cols && row >= 0 && row < rows && !visited[row][col] && arr[row][col] == 1) {
            // 上下左右去走
            visited[row][col] = true;
            // 上下左右
            res = isOk(step, col, cols, row + step, rows, arr, visited)
                    || isOk(step, col, cols, row - step, rows, arr, visited)
                    || isOk(step, col + step, cols, row, rows, arr, visited)
                    || isOk(step, col - step, cols, row, rows, arr, visited);
            visited[row][col] = false;

        }
        return res;
    }
}
