package com.duanxi.video.array;

/**
 * @author caoduanxi
 * @2019/12/3 20:55
 * leetcode 75 sort colors 将0 1 2进行排序
 * <p>
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortColors {
    /**
     * 这道题目的思路就是三路快排的思路
     * 三个变量，中间的是等于，后面的是大于，前面的是小于，大于和小于都交换顺序，直到i等two截止
     */
    // 题目思路就是将所有的值排序为0 1 2的格式即可
    public void sortColors(int[] nums) {
        int zero = -1;
        int two = nums.length;
        // 这里由于涉及到i的交换顺序，所以不能主动i++,而是根据条件判断
        // 因为[two-1...n-1]都是大于1的
        // [zero+1...i]都是小于1
        for (int i = 0; i < two; ) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] > 1) {
                two--;
                swap(nums, i, two);
                // 否则就为0
            } else {
                zero++;
                // 跟0交换之后需要前进
                swap(nums, zero, i++);
            }
        }
    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}





























