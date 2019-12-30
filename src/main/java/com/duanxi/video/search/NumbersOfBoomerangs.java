package com.duanxi.video.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caoduanxi
 * @Date 2019/12/30 21:30
 * leetcode 447 回旋镖的数量
 * <p>
 * 给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，
 * 其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 * <p>
 * 找到所有回旋镖的数量。你可以假设 n 最大为 500，所有点的坐标在闭区间 [-10000, 10000] 中。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [[0,0],[1,0],[2,0]]
 * <p>
 * 输出:
 * 2
 * <p>
 * 解释:
 * 两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-boomerangs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumbersOfBoomerangs {
    public static int numberOfBoomerangs(int[][] points) {
        if (points == null
                || points.length <= 0 || points[0].length <= 0)
            return 0;
        // 遍历所有的算出距离，然后排列组合即可
        int rows = points.length;
        // 如何算出所有的距离
        // 第一个存距离，第二个存个数
        Map<Long, Integer> map = new HashMap<>();
        // points[0]就是第一个元素
        int res = 0;
        for (int i = 0; i < rows; i++) {
            map.clear();
            for (int j = 0; j < rows; j++) {
                if (i == j) continue;
                long distance = getDistance(points[i][0], points[i][1], points[j][0], points[j][1]);
                // 当时就计算,潜移默化，因为这里限定了只有i j k组成，所以一定是乘以2
//                res += map.getOrDefault(distance, 0) * 2;
                map.put(distance, map.getOrDefault(distance, 0) + 1);
                // 这种好理解
                res += (map.get(distance) - 1) * 2;
            }
        }
        return res;
    }

    public static long getDistance(int a, int b, int c, int d) {
        return (long) Math.pow(Math.abs(a - c), 2) + (long) Math.pow(Math.abs(b - d), 2);
    }

    public static void main(String[] args) {
        int[][] test = new int[][]{{0, 0}, {1, 0}, {2, 0}};
        System.out.println(numberOfBoomerangs(test));
//        for (int i = 0; i < test.length; i++) {
//            for (int j = 0; j < test[0].length; j++) {
//                System.out.println(test[i][j]);
//            }
//        }
//        System.out.println(test[0][0]);
    }
}
