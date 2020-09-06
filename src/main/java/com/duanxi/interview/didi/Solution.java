package com.duanxi.interview.didi;

/**
 * @author caoduanxi
 * @Date 2020/9/2 8:21
 * 寻找前面递增后面递减的数组的最大值
 * 数组也可能一直递增，也可能一直递减
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(findMaxNum(new int[]{8, 10, 9, 6, 2}));
        System.out.println(findMaxNum(new int[]{3, 4, 5, 6, 7, 8, 9, 10}));
        System.out.println(findMaxNum(new int[]{10, 9, 8, 7, 6, 5}));
    }

    public static int findMaxNum(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        // 递增递减的情况
        int m_low = arr[0];
        int m_high = arr[arr.length - 1];
        // 利用夹逼的性质，通过中间的点对于两边来做判断
        int n = arr.length;
        int left = 0;
        int right = n - 1;
        int mid = (left + right) >> 1;
        int max = Integer.MIN_VALUE;
        // 如果是连续递增的话不可行
        // 如果是连续递减的话不可行
        // 这是查找中间元素的情况，必定是先递增再递减
        while (left < right) {
            if (arr[mid] > arr[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
            mid = (left + right) >> 1;
        }
        return Math.max(m_low, Math.max(m_high, arr[left]));
    }

    // 这里假设他是递增或者递减的
//    private static int getMaxNum(int[] arr) {
//        int n = arr.length;
//        int left = 0;
//        int right = n - 1;
//        int mid = (left + right) >> 1;
//        while (left <= right) {
////            if (right - left == 1) {
////                if (arr[right] > arr[left]) {
////                    return arr[right];
////                }
////                return arr[left];
////            }
//            if (arr[mid] > arr[left]) {
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//            mid = (left + right) >> 1;
//        }
//        return arr[left];
//    }
    // 因为二分查找必定有序
    private static int binarySearch(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] > arr[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return arr[left];
    }
}
