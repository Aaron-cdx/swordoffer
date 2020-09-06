package com.duanxi.interview.ZTE.one;

import java.util.Scanner;

/**
 * @author caoduanxi
 * @Date 2020/8/31 10:00
 * 寻找逆序对数
 * 即通过归并排序找到
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        System.out.println(0.75*1000000);
        int n = scanner.nextInt();
        int[] arr = new int[2*n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        for (int i = n; i < 2*n; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println(main.InversePairs(arr));

    }

    public long InversePairs(int[] array) {
        int n = array.length;
        if (n == 0 || array == null) return 0;

        int[] copy = new int[n];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        return inversePairsExecute(array, copy, 0, n - 1);
    }

    /**
     * 以 7 5 6 4 为例子，此含有逆序对数量为5--->{7 5 、7 6 、7 4、 5 4、 6 4}
     * 首先二分到7 5, 7 5 继续二分，最后回退到 7 5
     * 7 5 比较的话，产生一个逆序对，此时copy数组变为 5 7
     * 6 4 类似这个过程，最后产生4 6
     * 回退到 5 7 4 6
     * 此时进行最后的比较，获取到5个逆序对
     */
    private long inversePairsExecute(int[] array, int[] copy, int start, int end) {
        // 如果只有一个数，形成不了逆序对，直接返回0
        if (start == end) return 0;

        // 计算中间位置在哪，执行二分
        int mid = start + (end - start) / 2;
        // 执行二分操作
        long leftCount = inversePairsExecute(array, copy, start, mid);
        long rightCount = inversePairsExecute(array, copy, mid + 1, end);
        // 到了二分的尽头，此时开始执行归并且计算逆序对的逻辑
        long count = 0; // 统计逆序对数量
        int left = mid; // 保持在mid处，这样可以获取到逆序对的数量
        int right = end;
        int locCopy = end; // 为了排序使用的copy下标
        while (left >= start && right > mid) {
            // 计算逆序对的逻辑并排序
            if (array[left] > array[right]) {
                // 7 5的时候，此时是大于,因为左边最大大于右边最大，故逆序对就为right - end的大小
                count += right - mid;
                // 同时注意赋值操作，因为有copy在这里保持一个总的逆序对数量的计算
                copy[locCopy--] = array[left--]; // 保证最大的在最后面
                // 这里需要判断一下count的大小
//                if (count >= 1000000007) {
//                    count %= 1000000007;
//                }
            } else {
                // 如果不大于的话，这个情况可以理解为 5 7 4 6 (根据二分第一次排完序之后获取的数组)
                // 此时5 并不大于 6，此时为了保证copy中大的数据放在最后
                copy[locCopy--] = array[right--];
            }
        }
        // 最后执行当前二分阶段数据的排序工作
        // 其实这里就类似于归并过程中利用一个序列将两个序列排好序
        // 而这里是利用两个指针，其实也就是两个序列排序的过程
        for (; left >= start; left--) {
            copy[locCopy--] = array[left];
        }
        for (; right > mid; right--) {
            copy[locCopy--] = array[right];
        }
        // 执行复制
        for (int i = start; i <= end; i++) {
            array[i] = copy[i];
        }
        return (leftCount + rightCount + count);
    }
}
