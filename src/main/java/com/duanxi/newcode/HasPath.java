package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/10 10:38
 * 牛客网：矩阵中的路径
 * <p>
 * 题目：
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串
 * 所有字符的路径。路径可以从矩阵中的任意一个格子开始，
 * 每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 * 例如 a b c e s f c s a d e e 矩阵中包含一条字符串"bcced"的路径，
 * 但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据
 * 了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 * <p>
 * a b c e
 * s f c s
 * a d e e
 */
public class HasPath {
    /**
     * 主要的思路就是
     * 通过遍历找到每一个字母，看看是否是与str中对应
     * 注意这里是一维数组，需要转化为二维的取值
     * 公式为row*cols+col 相当于取值[row][col]
     * <p>
     * 经过一个字符，注意访问为true，不能够再次访问。
     * 如果没找到注意回溯，并且访问置为false
     */
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || rows < 1 || cols < 1 || str == null) return false;
        boolean[] visited = new boolean[matrix.length];
        // 通过遍历的方法找到str中开始的字符
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // 找到了的话，返回true
                if (hasPathCore(matrix, rows, row, cols, col, str, 0, visited)) {
                    return true;
                }
            }
        }
        // 没找到返回false
        return false;
    }

    private boolean hasPathCore(char[] matrix, int rows, int row, int cols, int col, char[] str, int index, boolean[] visited) {
        if (index == str.length) return true;
        boolean hasPath = false;
        if (row < rows && row >= 0 && col < cols && col >= 0 && !visited[row * cols + col] && (matrix[row * cols + col] == str[index])) {
            ++index;
            // 首先肯定是访问了
            visited[row * cols + col] = true;
            hasPath = hasPathCore(matrix, rows, row + 1, cols, col, str, index, visited)
                    || hasPathCore(matrix, rows, row - 1, cols, col, str, index, visited)
                    || hasPathCore(matrix, rows, row, cols, col - 1, str, index, visited)
                    || hasPathCore(matrix, rows, row, cols, col + 1, str, index, visited);
            if (!hasPath) {
                // 如果还没找到的话，只能回退了
                visited[row * cols + col] = false;
                --index;
            }
        }
        return hasPath;
    }

    // 从str中找出是否有含有str中字符串的路径，有true，没有false
    public static void main(String[] args) {
        HasPath test = new HasPath();
        boolean b = test.hasPath("ABCESFCSADEE".toCharArray(), 3, 4, "ABCB".toCharArray());
        System.out.println(b);
    }
}
