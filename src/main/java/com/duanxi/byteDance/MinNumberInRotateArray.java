package com.duanxi.byteDance;

/**
 * @author caoduanxi
 * @Date 2020/3/11 22:56
 * 字节跳动：旋转数组的最小数字
 * <p>
 * 题目：
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class MinNumberInRotateArray {
    public int minNumberInRotateArray(int[] array) {
        if (array.length == 0) return 0;
        // 找递增排序的最小数
        // 利用双指针查找元素
        int l = 0;
        int r = array.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            // 如果前中后相等，只能采用遍历
            if(array[l] == array[mid] && array[r] == array[mid]){
                int min = Integer.MAX_VALUE;
                for (int arr : array) {
                    min = Math.min(arr,min);
                }
                return min;
            }
            // 否则采用二分法
            if(array[mid] > array[r]){ // 如果大于右边的话，表明小的在右边，自己肯定不是最小的
                l = mid + 1;
            }else{ // 如果大于等于左边的话，自己可能是最小的
                r = mid;
            }
        }
        return array[l];
    }
}
