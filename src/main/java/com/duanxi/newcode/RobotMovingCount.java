package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/10 12:16
 * 牛客网：机器人的运动范围
 * <p>
 * 题目：
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，
 * 每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，
 * 它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 */
public class RobotMovingCount {
    public int movingCount(int threshold, int rows, int cols) {
        if (threshold <= 0) return 0;
        boolean[][] visited = new boolean[rows][cols];
        return getMovingCount(threshold, rows, 0, cols, 0, visited);
    }

    // 从(0,0)开始,计算往四个方向去的路径有多少！
    private int getMovingCount(int threshold, int rows, int row, int cols, int col, boolean[][] visited) {
        int count = 0;
        // 判断是否能进去的准则
        if (row >= 0 && row < rows && col >= 0 && col < cols && !visited[row][col] && (getSum(row) + getSum(col) <= threshold)) {
            count = 1;
            visited[row][col] = true;
            count += getMovingCount(threshold, rows, row - 1, cols, col, visited) +
                    getMovingCount(threshold, rows, row + 1, cols, col, visited) +
                    getMovingCount(threshold, rows, row, cols, col - 1, visited) +
                    getMovingCount(threshold, rows, row, cols, col + 1, visited);
        }
        return count;
    }


    private int getSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        RobotMovingCount test = new RobotMovingCount();
        int i = test.movingCount(5, 10, 10);
        System.out.println(i);
    }
}
