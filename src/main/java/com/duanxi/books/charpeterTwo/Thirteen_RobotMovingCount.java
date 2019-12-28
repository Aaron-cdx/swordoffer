package com.duanxi.books.charpeterTwo;

/**
 * @author caoduanxi
 * @Date 2019/12/27 22:19
 * 剑指offer 面试题13 机器人的运行范围
 * <p>
 * 题目：地上有一个m行n列的方格，一个机器人从坐标(0,0)的格子开始移动
 * 它每次可以向左、向右、向上、向下移动一格，但不能进入行坐标和列坐标的数位之和大于k的格子,也不能进入相同的格子
 * 例如，当k为18时，机器人能够进入方格(35,37)，因为3+5+3+7=18，但是不能进入方格(35,38)，因为3+5+3+8=19
 * 请问该机器人能够到达多少个格子？
 */
public class Thirteen_RobotMovingCount {
    public static int movingCount(int threshold, int rows, int cols) {
        // 判断边界条件
        if (threshold < 0 || rows <= 0 || cols <= 0) return 0;
        boolean[][] visited = new boolean[rows][cols];
        // 需要指明当前坐标在哪,初始坐标需要指明在哪
        return movingCountCore(threshold, rows, cols, 0, 0, visited);
    }

    private static int movingCountCore(int threshold, int rows, int cols, int row, int col, boolean[][] visited) {
        int count = 0;
        // 迭代的话使用回溯法迭代求解
        if (row >= 0 && row < rows && col >= 0 && col < cols && !visited[row][col] && threshold <= (getSum(rows) + getSum(cols))) {
            visited[row][col] = true;
            // 当前+上下左右
            count = 1 + movingCountCore(threshold, rows, cols, row - 1, col, visited)
                    + movingCountCore(threshold, rows, cols, row + 1, col, visited)
                    + movingCountCore(threshold, rows, cols, row, col - 1, visited)
                    + movingCountCore(threshold, rows, cols, row, col + 1, visited);
        }
        return count;
    }

    public static int getSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(movingCount(4, 2, 2));
    }
}
