package com.duanxi.books.charpeterTwo;

/**
 * @author caoduanxi
 * @Date 2019/12/20 22:18
 * 剑指offer 面试题11
 * <p>
 * 题目：
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素
 * 例如：数组为{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1
 * <p>
 * 考察能力：
 * 对二分法的理解！
 */
public class Eleven_MinNumber {
    // 利用双指针+二分法
    public static int Min1(int[] nums) throws Exception {
        if (nums == null || nums.length <= 0) throw new Exception("错误的输入参数");
        int l = 0;
        int r = nums.length - 1;
        int min = l;
        while (nums[l] >= nums[r]) {
            // 表示两个指针相遇
            if (r - l == 1) {
                min = r;
                break;
            }
            // 此时第一个数为大，此时需要直接到中间,如果还是大的话，
            min = l + (r - l) / 2;
            // 只有两种可能此时的中中间大于最后一个数，或者小于最后一个数，如果等于的话就是另外一种情况了
            if (nums[min] >= nums[r]) {
                l = min;
            } else if (nums[min] <= nums[r]) {
                // 此时表明中间数为后一组，旋转之前的前面
                r = min;
            }
        }
        return nums[min];
    }

    public static int Min(int[] nums) throws Exception {
        if (nums == null || nums.length <= 0) throw new Exception("错误的输入参数");
        int l = 0;
        int r = nums.length - 1;
        int min = l;
        while (nums[l] >= nums[r]) {
            // 表示两个指针相遇
            if (r - l == 1) {
                min = r;
                break;
            }
            // 此时第一个数为大，此时需要直接到中间,如果还是大的话，
            min = l + (r - l) / 2;
            // 如果三个数相等，则按照最小顺序来，则不能采用二分，只能老老实实遍历整个数组找出最小值，
            /** 这里要学会变通！！！！！！！因为你没有办法采用，为了结果准确损失一点性能是没有什么的！ */
            if (nums[l] == nums[min] && nums[min] == nums[r]) {
                min = minOrder(nums, l, r);
            }

            if (nums[min] >= nums[r]) {
                l = min;
            } else if (nums[min] <= nums[r]) {
                // 此时表明中间数为后一组，旋转之前的前面
                r = min;
            }
        }
        return nums[min];
    }

    private static int minOrder(int[] nums, int l, int r) {
        int min = l;
        for (int i = l + 1; i <= r; i++) {
            if (nums[min] > nums[i]) {
                min = i;
            }
        }
        return min;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(Min(new int[]{3, 4, 5, 1, 2}));
        System.out.println(Min(new int[]{1, 0, 1, 1, 1}));
        System.out.println(Min(new int[]{1, 1, 1, 0, 1}));
        // 此时需要判断相等的情况
        // 特殊情况的话{1,0,1,1,1}--{1,1,1,0,1}=>{0,1,1,1,1}两个求出来的大小不同
    }
}
