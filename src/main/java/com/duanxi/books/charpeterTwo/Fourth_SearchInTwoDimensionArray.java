package com.duanxi.books.charpeterTwo;

/**
 * @author caoduanxi
 * @2019/12/5 21:27
 * 在二维数组中查找数字
 * 题目：
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断此二维数组中是否有该数字？
 */
public class Fourth_SearchInTwoDimensionArray {
    public static boolean hasTheNumber(int[][] nums, int num) {
        boolean found = false;
        // 对边界条件作分析，很重要
        if (nums != null && nums.length > 0 && nums[0].length > 0) {
            int i = 0;
            int j = nums[0].length - 1;
            // 从左到右是大，从上到下是大
            while (i < nums.length && j >= 0) {
                // 如果是大于的话，向左查找
                if (nums[i][j] > num) {
                    j--;
                    // 如果是小于的话，向下查找
                } else if (nums[i][j] < num) {
                    i++;
                } else {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        System.out.println(hasTheNumber(nums,100));
    }
}
