package com.duanxi.sortingAlgorithm;

/**
 * @author caoduanxi
 * @Date 2019/12/19 18:39
 * 排序-基数排序
 * <p>
 * 思想：
 * 从个位数开始排布，然后十位，百位......当然也可以反过来
 * 这其中基数其实就是10，一般只有0-9，桶的个数就取数组中的个数即可，需要装下
 * <p>
 * 时间复杂度：O(d(rd+n))
 * 空间复杂度：O(rd+n)
 * 稳定性：稳定（入桶的话都有j在把控，所以先入的这个肯定是后进桶(从后向前，所以还是在前面)）
 */
public class RadixSort {

    public static void radixSort(int[] nums) {
        sort(nums, 0, nums.length - 1, getMaxBit(nums));
    }

    public static void sort(int[] nums, int start, int end, int digit) {
        final int radix = 10;
        int i;
        int j;
        int[] count = new int[radix];
        // bucket桶的数量不一定有count数量多
        int[] bucket = new int[end - start + 1];
        for (int d = 1; d <= digit; d++) {
            // 将按照数字的count全都置零
            for (i = 0; i < count.length; i++) {
                count[i] = 0;
            }
            // 计算每个count中响应位数大小的个数
            for (i = start; i <= end; i++) {
                count[getDigit(nums[i], d)]++;
            }
            // 计算总的个数
            for (i = 1; i < count.length; i++) {
                count[i] = count[i] + count[i - 1];
            }
            /**
             * 这里很精妙
             * 首先count[j]统计的是所有的个数，他们的个数一定不超过end-start+1
             * 这里的话就是按照上面位数大小从后向前放入count中
             * 其实每一轮都是排好序的，j在主导着这个顺序
             */
            // 将所有的数放进桶中，桶的个数正好是count[j]的个数
            for (i = end; i >= start; i--) {
                j = getDigit(nums[i], d);
                // 将当前的放进最后一个桶了
                bucket[count[j] - 1] = nums[i];
                count[j]--;
            }
            // 元素都放进去之后，此时进行赋值
            for (i = start, j = 0; i <= end; i++, j++) {
                nums[i] = bucket[j];
            }
        }

    }

    public static int getMaxBit(int[] nums) {
        int bitNum = 0;
        for (int i = 0; i < nums.length; i++) {
            int cnt = 0;
            int temp = nums[i];
            while (temp != 0) {
                cnt++;
                temp /= 10;
            }
            bitNum = Math.max(bitNum, cnt);
        }
        return bitNum;
    }

    public static int getDigit(int x, int d) {
        return (int) ((x / Math.pow(10, d - 1)) % 10);
    }

    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{8, 7, 5, 3, 2, 1};
        int[] nums = new int[]{12, 15, 9, 20, 6, 31, 212, 34, 67, 231, 786, 1122, 334455, 234};
        radixSort(nums);
        System.out.print("[ ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }
}
