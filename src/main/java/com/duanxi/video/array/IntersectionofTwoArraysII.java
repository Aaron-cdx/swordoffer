package com.duanxi.video.array;

import java.util.*;

/**
 * @author caoduanxi
 * @2019/12/12 20:59
 * leetcode 350 两个数组的交集II
 * <p>
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 示例 2:
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 说明：
 * <p>
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶:
 * <p>
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IntersectionofTwoArraysII {
    // 通过了，但是时间空间复杂度都过高
    // 可以使用ArrayList来存储数据，避免使用map存储第一次的数据，这样的话比较容易遍历
    public static int[] intersect1(int[] nums1, int[] nums2) {
        // 标准的使用map结构来解决问题
        if (nums1 == null || nums2 == null) return null;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 出现了才存
        Map<Integer, Integer> resMap = new HashMap<>();
        for (int num : nums2) {
            if(map.containsKey(num) && map.get(num) > 0){
                map.put(num,map.getOrDefault(num,1)-1);
                resMap.put(num, resMap.getOrDefault(num, 0) + 1);
            }
        }
        // 获取总的元素个数
        Set<Integer> set1 = resMap.keySet();
        Iterator<Integer> iterator = set1.iterator();
        int sum = 0;
        while(iterator.hasNext()){
            sum += resMap.get(iterator.next());
        }
        // 建立sum大小的数组
        int[] res = new int[sum];
        Set<Integer> set = resMap.keySet();
        Iterator<Integer> iterator1 = set.iterator();
        int i = 0;
        // 赋值
        while(iterator1.hasNext()){
            int key = iterator1.next();
            int val = resMap.get(key);
            for (int j = 0; j < val; j++) {
                res[i++] = key;
            }
        }
        // 输出结果
        return res;
    }


    // 排序预处理
    // 遇到相等，都往前，否则小的向前冲，因为小的后面有大的
    public static int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null) return null;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        List<Integer> list = new ArrayList<>();
        // 小的向前走，这样才可能遇到大的与对方相等
        while(i < nums1.length && j < nums2.length){
            if(nums1[i] == nums2[j]){
                list.add(nums1[i]);
                i++;
                j++;
            }else if(nums1[i] < nums2[j]){
                i++;
            }else{
                j++;
            }
        }
        int[] nums = new int[list.size()];
        int k = 0;
        for (Integer integer : list) {
            nums[k++] = integer;
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] intersect = intersect(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4});
        for (int i : intersect) {
            System.out.print(i+" ");
        }
    }
}
