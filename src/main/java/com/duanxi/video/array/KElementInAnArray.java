package com.duanxi.video.array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author caoduanxi
 * @2019/12/4 20:20
 * leetcode 215 数组中第k个最大的元素
 * <p>
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KElementInAnArray {
    // 直接排序，然后查找
    // 时间复杂度O(nlogn)
    // 空间复杂度O(1)
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }


    public int findKthLargest1(int[] nums, int k) {
        // 使用大顶堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.poll();
    }

    // 利用快速选择排序算法解题
    public int findKthLargest2(int[] nums, int k) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        // 找到这个的下标为target的就证明找到了
        int target = len - k;
        while (true) {
            int index = partition(nums, left, right);
            if (index < target) {
                left = index + 1;
            } else if (index > target) {
                right = index - 1;
            } else {
                return nums[index];
            }
        }
    }

    // 这里的分区算法主要就是找到一个基准，在这个基准前面的数都比这个数要小，后面的都比自己要大
    private int partition(int[] nums, int left, int right) {
        int privot = nums[left];
        // 这个值充当分区指针，向前走，以交换
        int j = left;
        // 这里分区的时候，实质left位置上的数字一直没动
        // 如果一直小，就一直原位交换
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < privot) {
                j++;
                swap(nums, j, i);
            }
        }
        // 这里是为了确保分区成功，因为分区之后，所有的数都回到了原位
        swap(nums, j, left);
        return j;
    }

    private void swap(int[] nums, int a, int b) {
        if(a == b) return;
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
























