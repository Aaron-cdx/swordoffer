package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/5 10:05
 * 牛客网：数字在排序数组中出现的次数
 * <p>
 * 题目：
 * 统计一个数字在排序数组中出现的次数。
 */
public class GetNumberOfK {
    public int GetNumberOfK(int[] array, int k) {
        // 思路先用二分法查找到这个数字，然后前后进行搜索，获取到出现次数
        /*int cnt = 0;
        // 应该使用二分法获取到k数字的下标
        cnt += getNumberOfK(array, 0, array.length - 1, k);
        return cnt;*/
        // 第二种方法的解答
        return binarySearch(array, 0, array.length - 1, (int) (k + 0.5))
                - binarySearch(array, 0, array.length - 1, (int) (k - 0.5));
    }

    /**
     * 这是利用二分查找前后的所有等于k的数字个数
     */
    // 注意这是一个排好序的数组，array[mid] 不可能大于 array[end]
    public int getNumberOfK(int[] array, int start, int end, int k) {
        if (start > end) return 0; // 递归终止条件
        if (start == end && array[start] == k) return 1;
        int mid = start + (end - start) / 2;
        if (array[mid] == k) {
            return 1 + getNumberOfK(array, start, mid - 1, k)
                    + getNumberOfK(array, mid + 1, end, k);
        } else if (array[mid] < k) {
            return getNumberOfK(array, mid + 1, end, k);
        } else {
            // 大于k
            return getNumberOfK(array, start, mid - 1, k);
        }
    }

    /**
     * 第二种方法：利用前后查找两个位置，然后利用两个位置相减即可
     * 这种方法的优势是找到相同数字前后的坐标，直接相减即可
     * 相比于第一种方法，这种方法的优势比较大
     */
    public int binarySearch(int[] array, int start, int end, int num) {
        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (array[mid] > num) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        GetNumberOfK test = new GetNumberOfK();
        int cnt = test.GetNumberOfK(new int[]{1, 2, 3, 4, 5, 5, 5, 6, 7, 8, 10}, 5);
        System.out.println(cnt);
    }
}
