package com.duanxi.interview.didi.two;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/8/21 18:51
 */
public class Main {
    private static long[][] matrix;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        int n = scanner.nextInt();
        long[] dp = main.getFeiBo(n);
        matrix = new long[n][n];
        int rows = n - 1;
        int cols = n - 1;
        int row = 0;
        int col = 0;
        main.print(dp,dp.length-1,row,rows,col,cols);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * 倒着打印即可
     */
    private void print(long[] dp,int index, int row, int rows, int col, int cols) {
        if(row > rows || col > cols) return;
        if (row == rows && col == cols){
            matrix[row][col] = dp[index];
            return;
        }
        // 否则的话此时需要将值填充起来
        int r = row;
        int c = col;
        while(col < cols){
            matrix[row][col++] = dp[index];
            index -= 1;
        }
        while(row < rows){
            matrix[row++][col] = dp[index];
            index -= 1;
        }
        while(col != c){
            matrix[row][col--] = dp[index];
            index -= 1;
        }
        while(row != r) {
            matrix[row--][col] = dp[index];
            index -= 1;
        }
        print(dp,index,r+1,rows-1,c+1,cols-1);
    }

    /**
     * 获取斐波那契数列
     */
    private long[] getFeiBo(int n) {
        long[] dp = new long[n * n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n * n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp;
    }
}
