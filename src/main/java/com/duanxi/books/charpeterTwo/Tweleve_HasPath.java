package com.duanxi.books.charpeterTwo;

/**
 * @author caoduanxi
 * @Date 2019/12/22 22:29
 * 剑指offer 面试题12 矩阵中的路径
 */
public class Tweleve_HasPath {
    /**
     * 是否含有规定的字符的矩阵
     *
     * @param matrix 矩阵
     * @param rows   行数
     * @param cols   列数
     * @param str    规定的字符
     * @return true/false
     */
    public static boolean hasPath(char[][] matrix, int rows, int cols, char[] str) {
        if (matrix == null || rows < 1 || cols < 1 || str == null) return false;
        // 默认为false
        boolean[][] visited = new boolean[rows][cols];
        int pathLength = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (hasPathCore(matrix, rows, row, cols, col, str, pathLength, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasPathCore(char[][] matrix, int rows, int row, int cols, int col, char[] str, int pathLength, boolean[][] visited) {
        // 用于判断到了第几个,如果到了末尾的话，直接判定为true，因为到了最后一个字符！
        if (pathLength >= str.length) return true;
        boolean hasPath = false;
        if (row >= 0 && row < rows && col >= 0 && col < cols
                && matrix[row][col] == str[pathLength] && !visited[row][col]) {
            ++pathLength;
            visited[row][col] = true;
            // 判断上下左右有没有符合的，有的话返回true，否则返回false
            hasPath = hasPathCore(matrix, rows, row + 1, cols, col, str, pathLength, visited)
                    || hasPathCore(matrix, rows, row - 1, cols, col, str, pathLength, visited)
                    || hasPathCore(matrix, rows, row, cols, col + 1, str, pathLength, visited)
                    || hasPathCore(matrix, rows, row, cols, col - 1, str, pathLength, visited);
            // 返回false的话需要回退，回溯开始
            if (!hasPath) {
                --pathLength;
                visited[row][col] = false;
            }
        }
        return hasPath;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{{'a', 'b', 't', 'g'}, {'c', 'f', 'c', 's'}, {'j', 'd', 'e', 'h'}};
        char[] str = new char[]{'b', 'f', 'c', 'e'};
        System.out.println(hasPath(matrix, matrix.length, matrix[0].length, str));
        System.out.println(hasPath(matrix, matrix.length, matrix[0].length, new char[]{'a','c','j','d','e','h'}));
        System.out.println(hasPath(matrix, matrix.length, matrix[0].length, new char[]{'a','b','c','d'}));
    }
}
