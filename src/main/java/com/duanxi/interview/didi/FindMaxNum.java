package com.duanxi.interview.didi;

public class FindMaxNum {
    /**
     * 查找先非严格递增后非严格递减的数组中的最大值
     * 利用二分查找法
     */
    private static int findMaxNum(int[] arr) {
        int index = 0;
        int length = arr.length;
        for (int i = 0, j = length - 1; i < j; ) {//i是下边界，j是上边界
            int mid = (i + j) / 2;

            //下面两个while循环找到左右不相等的边界或者数组的边界
            int left = mid - 1;
            while (arr[left] == arr[mid] && left > 0) {
                left--;
            }
            int right = mid + 1;
            while (arr[right] == arr[mid] && right < length - 1) {
                right++;
            }

            //通过判断当前mid坐在位置，上坡，下坡或者坡顶来找到最大值
            if (arr[mid] > arr[left] && arr[mid] < arr[right]) {
                i = right;
            } else if (arr[mid] < arr[left] && arr[mid] > arr[right]) {
                j = left;
            } else if (arr[mid] >= arr[left] && arr[mid] >= arr[right]) {//这个地方需要以等号作为条件，因为可能最大值在边界处
                index = mid;
                break;
            }
        }
        return arr[index];
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3, 4, 5, 6, 7, 7, 5, 5, 5, 5, 4, 4, 4, 4, 2, 2, 2, 1, 1};
        int[] arr2 = new int[]{7, 7, 7, 5, 5, 5, 5, 4, 4, 4, 4, 2, 2, 2, 1, 1};
        int[] arr3 = new int[]{1, 2, 3, 4, 5, 5, 5, 5, 5, 6, 7, 7, 7};
        int[] arr4 = new int[]{7, 7, 7, 7, 7, 7};
        int[] arr5 = new int[]{3, 4, 5, 6, 7, 8, 9, 10};
        int maxNum = findMaxNum(arr1);
        int maxNum2 = findMaxNum(arr2);
        int maxNum3 = findMaxNum(arr3);
        int maxNum4 = findMaxNum(arr4);
        int maxNum5 = findMaxNum(arr5);
        System.out.println(maxNum);
        System.out.println(maxNum2);
        System.out.println(maxNum3);
        System.out.println(maxNum4);
        System.out.println(maxNum5);
    }
}

