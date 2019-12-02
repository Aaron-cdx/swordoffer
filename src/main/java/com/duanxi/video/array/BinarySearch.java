package com.duanxi.video.array;

/**
 * @author caoduanxi
 * @2019/12/2 19:39
 * 二分查找法的实现
 */
public class BinarySearch {
    /**
     * 二分法实现指定target的查找，查找到了返回下标，否则返回-1
     *
     * @param nums
     * @param target
     * @return
     */
    public static int getNumIndex(int[] nums, int target) {
        int n = nums.length;
        // 指定边界
        int l = 0;
        int r = n;
        // 循环查找
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) // 注意范围[l....r)
                return mid;
            if (target > nums[mid]) // [mid+1...r)
                l = mid + 1;
            if (target < nums[mid]) // [l...mid)
                r = mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 1000000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i;
        }
        long startTime = System.currentTimeMillis();
        int numIndex = getNumIndex(nums, 998774);
        long endTime = System.currentTimeMillis();
        if (numIndex == -1) {
            try {
                throw new Exception("查找错误");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("耗时:" + (endTime - startTime) + "ms");
        }
    }
}
