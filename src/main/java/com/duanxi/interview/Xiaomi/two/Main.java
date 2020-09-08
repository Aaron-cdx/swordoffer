package com.duanxi.interview.Xiaomi.two;

import java.util.Scanner;

/**
 * @Author: caoduanxi
 * @Date: 2020/9/8
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        char[][] words = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String str = scanner.nextLine();
        if(str == null || str.length() == 0 || str.trim().length() == 0){
            System.out.println(false);
            return;
        }
        System.out.println(main.existWord(words, words.length, words[0].length, str.toCharArray()));
    }

    public boolean existWord(char[][] matrix, int rows, int cols, char[] str) {
//        if (matrix == null || rows < 1 || cols < 1 || str == null) return false;
        boolean[][] visited = new boolean[rows][cols];
        // 通过遍历的方法找到str中开始的字符
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // 找到了的话，返回true
                if (executeWordSearch(matrix, rows, row, cols, col, str, 0, visited)) {
                    return true;
                }
            }
        }
        // 没找到返回false
        return false;
    }

    private boolean executeWordSearch(char[][] matrix, int rows, int row, int cols, int col, char[] str, int index, boolean[][] visited) {
        if (index == str.length) return true;
        boolean hasPath = false;
        if (row < rows && row >= 0 && col < cols && col >= 0 && !visited[row][col] && (matrix[row][col] == str[index])) {
            ++index;
            // 首先肯定是访问了
            visited[row][col] = true;
            hasPath = executeWordSearch(matrix, rows, row + 1, cols, col, str, index, visited)
                    || executeWordSearch(matrix, rows, row - 1, cols, col, str, index, visited)
                    || executeWordSearch(matrix, rows, row, cols, col - 1, str, index, visited)
                    || executeWordSearch(matrix, rows, row, cols, col + 1, str, index, visited);
            // 如果还没找到的话，只能回退了
            if (!hasPath) {
                visited[row][col] = false;
                --index;
            }
        }
        return hasPath;
    }
}
