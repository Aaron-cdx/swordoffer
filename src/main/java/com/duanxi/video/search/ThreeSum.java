package com.duanxi.video.search;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2019/12/17 20:32
 * leetcode 15 三数之和
 * <p>
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ThreeSum {
    // 暴力法三重循环解决这个问题，但是不能使用暴力法，时间复杂度超级高
    // 本来想用map来解决，但是map存在重复，不能够取出，是指虽然键key不同，但是value相同的时候，没有办法避免
    /*public static List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>(3);
        // 双指针
        Arrays.sort(nums);
        int i = 0;
        int j = 1;
        int k = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        while (i < nums.length - 1 && j < nums.length) {
            List<Integer> temp = new ArrayList<>();
            temp.add(nums[i]);
            temp.add(nums[j]);
            map.put(-(nums[i] + nums[j]), temp);
            i++;
            j++;
        }
        for (int t = 0; t < nums.length; t++) {
            if ((t > 0 && nums[t] != nums[t - 1]) && map.containsKey(nums[t])) {
                List<Integer> list = map.get(nums[t]);
                list.add(nums[t]);
                res.add(list);
            }
        }
        return res;
    }*/

    private static List<List<Integer>> threeSum(int[] nums) {
        // 边界问题需要考虑到位
        if (nums == null || nums.length < 3) return null;
        // 使用双指针对撞找到答案
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (nums[i] != nums[i - 1])) {
                // 指针对撞来解决
                int j = i + 1;
                int k = nums.length - 1;
                int sum = 0 - nums[i];
                while (j < k) {
                    // 在这里需要去重
                    if (nums[j] + nums[k] > sum) {
                        k--;
                    } else if (nums[j] + nums[k] < sum) {
                        j++;
                    } else {
                        // 等于的话直接添加
                        int t = k - 1;
                        // 这里是去除重复，一直讲相等的值往前逼
                        while (t > j) {
                            if (nums[t] != nums[k])
                                break;
                            t--;
                        }
                        res.add(Arrays.asList(nums[i], nums[j], nums[t + 1]));
                        j++;
                        // 注意这里不要忘记把k移动到t的位置
                        k = t;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(threeSum(new int[]{-2, 0, 0, 2, 2}));
    }
}
