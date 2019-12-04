package com.duanxi.video.array;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @2019/12/4 19:11
 * leetcode 80 合并两个有序数组
 * <p>
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeSortedArray {
    // 思路不利用新的数组
    // 这个思路错了！！！！
    // 1.当2中的数大于1中的数，1向后走，2不动
    // 2.当2中的数小于1中的数，将1中的数替换到2这个位置，2的数移动到1的位置
    // [4 5 6 0 0 0]
    // [1 2 3]        ==> {1 2 3 4 5 6}
    // 新的思路：
    // 应该使用尾部填充法，因为两个数组最大的值都在最后面，所以谁大就谁取填充
    // 时间复杂度
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = nums1.length;
        if (len == m) return;
        len--;
        int a = m - 1;
        int b = n - 1;
        while (a >= 0 && b >= 0) {
            if (nums1[a] > nums2[b]) {
                nums1[len--] = nums1[a--];
            } else {
                nums1[len--] = nums2[b--];
            }
        }
        // 注意如果b到头了，证明数据全都进去了
        // 如果b没有到头，证明b位置之前的数都要复制进去
        if (b != -1) {
            System.arraycopy(nums2, 0, nums1, 0, b + 1);
        }
    }

    // 暴力解法，还借助了排序，需要优化
    // 时间复杂度(n+m)log(n+m)
    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        int len = nums1.length;
        if (len == m) return;
        int a = 0;
        for (int i = m; i < len; i++) {
            nums1[i] = nums2[a++];
        }
        for (int s : nums1) {
            System.out.println(s);
        }
        Arrays.sort(nums1);
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 5, 6, 0, 0, 0};
        int[] nums2 = new int[]{1, 2, 3};
        merge(nums1, 3, nums2, 3);
        System.out.println("===============");
        for (int i : nums1) {
            System.out.println(i);
        }
    }
}

























