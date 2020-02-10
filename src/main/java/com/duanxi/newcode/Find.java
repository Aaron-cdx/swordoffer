package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/10 18:07
 * 牛客网：二维数组中的查找
 * <p>
 * 题目：
 * 在一个二维数组中（每个一维数组的长度相同），
 * 每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class Find {
    public boolean Find(int target, int[][] array) {
        int row = 0;
        int column = array[0].length - 1;
        // 从左上角开始查找
        // 由于是左右递增 上下递增
        while (row <= array.length - 1 && column >= 0) {
            // 如果当前值大于目标值则向左移动
            if (array[row][column] > target) {
                column--;
                // 如果小于的话则向下移动
            } else if (array[row][column] < target) {
                row++;
            } else { // 查找到了返回true
                return true;
            }
        }
        return false;
    }
}
