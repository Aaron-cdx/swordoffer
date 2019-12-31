package com.duanxi.video.search;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2019/12/30 22:24
 * leetcode 149 直线上最多的点数
 * <p>
 * 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,1],[2,2],[3,3]]
 * 输出: 3
 * 解释:
 * ^
 * |
 * |        o
 * |     o
 * |  o  
 * +------------->
 * 0  1  2  3  4
 * 示例 2:
 * <p>
 * 输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出: 4
 * 解释:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-points-on-a-line
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxPointsInALine {
    /**
     * 本题采用最大公约数，将在同一条斜率上的点确定，同时将在同一条线上的点也确定
     * 在同一点的点采用repeat记录，最后获得在同一斜率上的点和重复点，获取答案
     *
     * @param points
     * @return
     */
    // 同一条线有三种情况
    // 在同一条x轴上，在同一条y轴上，或者在同一条y=x的线上
//    public static int maxPoints1(int[][] points) {
//        int rows = points.length;
//        Map<String, Integer> map = new HashMap<>();
//        int res = 0;
//        for (int i = 0; i < rows; i++) {
//            map.clear();
//            int tempmid = 0;
//            int repeat = 0;
//            for (int j = 0; j < rows; j++) {
//                if (i == j) continue;
//                int dis1 = (points[i][0] - points[j][0]);
//                int dis2 = (points[i][1] - points[j][1]);
//                // 如果为自己的话就+,有重叠必为偶数
//                if (dis1 == 0 && dis2 == 0) {
//                    repeat++;
//                    continue;
//                }
//                // 如果是[0,0] [1,1] [1,-1]就会出现3个点在一条直线上，实质是两个，两者的斜率是不同的
//                // 此时只要判断距离即可，如果两点到[0,0]斜率相等的话，那么可以断定，两者可能在同一条直线上
//                // 此时只要判断两者之间的斜率是否等于这个斜率
//                // 直接算斜率
//                String temp;
//                if (dis1 != 0) {
//                    double len = dis2 / dis1;
//                    temp = len+"#";
//                } else {
//                    temp = "#";
//                }
//                map.put(temp, map.getOrDefault(temp, 0) + 1);
//                tempmid = Math.max(tempmid, map.get(temp));
//            }
//            res = Math.max(res, repeat + tempmid + 1);
//        }
//        return res;
//    }

    // 使用最大公约数来求解
    public static int maxPoints(int[][] points) {
        int n = points.length;
        if (n == 0) return 0;
        if (n == 1) return 1;
        int res = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            map.clear();
            int repeat = 0;
            int tmpMax = 0;
            for (int j = i + 1; j < n; j++) {
                int dx = points[i][0] - points[j][0];
                int dy = points[i][1] - points[j][1];
                if (dx == 0 && dy == 0) {
                    repeat++;
                    // 此时就不用计算斜率
                    continue;
                }
                // 否则求最大公约数，主要是判断在一条直线上的斜率是否相同
                int g = gcd(dy, dx);
                if (g != 0) {
                    dy /= g;
                    dx /= g;
                }
                // 通过string来组合，因为dy/dx,dx很有可能为0，如果采用int，实现不了
                String tempString = dy + "/" + dx;
                map.put(tempString, map.getOrDefault(tempString, 0) + 1);
                tmpMax = Math.max(tmpMax, map.get(tempString));
            }
            // 需要加上重复的数，也就是在同一点的数
            res = Math.max(res, tmpMax + repeat + 1);
        }
        return res;
    }

    public static int gcd(int dy, int dx) {
        // 只要余出来为0，就找到了最大公约数
        if (dx == 0) return dy;
        return gcd(dx, dy % dx);
    }

    public static void main(String[] args) {
        int[][] test = new int[][]{{1, 1}, {2, 2}, {3, 3}};
        int[][] test1 = new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        int[][] test2 = new int[][]{{1, 1}, {2, 2}, {3, 3}, {0, 0}};
        int[][] test3 = new int[][]{{1, 1}, {0, 0}, {1, -1}};
        int[][] test4 = new int[][]{{1, 1}, {2, 2}, {1, 1}, {2, 2}};
        int[][] test5 = new int[][]{{0, 0}, {65536, 0}, {1, 65536}};
//        System.out.println(maxPoints(test));
//        System.out.println(maxPoints(test1));
//        System.out.println(maxPoints(test2));
//        System.out.println(maxPoints(test3));
//        System.out.println(maxPoints(test4));
//        System.out.println(maxPoints(test5));
//        System.out.println(maxPoints1(test1));
//        System.out.println(maxPoints1(test2));
//        System.out.println(maxPoints1(test3));
//        System.out.println(maxPoints1(test4));
//        System.out.println(maxPoints1(test5));
    }
}
