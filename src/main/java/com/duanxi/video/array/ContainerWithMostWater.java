package com.duanxi.video.array;

/**
 * @author caoduanxi
 * @2019/12/7 19:53
 * leetcode 11 盛水最多的容器
 * <p>
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * <p>
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例:
 * <p>
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ContainerWithMostWater {
    // 这个要下标之差最大，值相差最小
    // [1,8,6,2,5,4,8,3,7]
    //  0 1 2 3 4 5 6 7 8
    public static int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int res = 0;
        // 这里关注的是成为矩形区域面积更大的可能性
        // 所以每次如果遇到大的就移动小的，因为大的已经出现，只有再出现大的这样面积最大的可能性就最大
        // 小的已经出现，如果改变大的，可能导致越来越小
        while (i < j) {
            res = Math.max(res, (j - i) * Math.min(height[i], height[j]));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }

    // 暴力法能解决问题
    // 时间复杂度O(n^2) 空间复杂度O(1)
    public static int maxArea1(int[] height) {
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int indexLen = j - i;
                int value = Math.min(height[i], height[j]);
                res = Math.max(res, indexLen * value);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}
