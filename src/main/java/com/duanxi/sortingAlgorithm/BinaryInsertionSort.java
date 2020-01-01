package com.duanxi.sortingAlgorithm;

/**
 * @author caoduanxi
 * @Date 2019/12/19 15:05
 * 排序-二分插入排序
 * <p>
 * 思想：
 * 找出具体需要比对的数是在哪，找到区间，实现区间平移，最后将temp放在low位置就好，二分法最后一定到达一个位置
 * <p>
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 * 稳定性：稳定
 */
public class BinaryInsertionSort {
    // 相比直接插入排序，这里使用折半比较，如果大于前半部分，则在后半部分插入，否则在前半部分插入
    // 因为前面i个已经排好序了
    public static void binaryInsertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int low = 0;
            int high = i - 1;
            int mid;
            while (low <= high) {
                mid = low + (high - low) / 2;
                // 大于的话只能在之前插入，否则就在后面插入
                if (nums[mid] > temp) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // 与j交换的不能够在low值之前，因为low就是区间的最小
            // 这是要平移所有的数
            for (int j = i - 1; j >= low; j--) {
                nums[j + 1] = nums[j];
            }
            nums[low] = temp;
        }
    }

    /**
     * 详细解释版本
     * 这里是通过每次插入第i个元素的时候，比较第i个元素与之前的元素中间元素对比，通过二分法。
     * 如果小的话表名在之前插入，如果大的话，表名在之后插入。直到low>high为止，然后数组移位元素
     * 将当前i记录，数组移位，然后填充即可
     *
     * @param nums
     */
    public static void binaryInsertion(int[] nums) {
        // 二分插入法-每次折半查找
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            // temp就是当前元素，low high主要是为了定位插入的位置
            int low = 0;
            int high = i - 1;
            int mid = 0;
            while (low <= high) {
                mid = low + ((high - low) / 2);
                // 注意比较的是mid
                if (nums[mid] > temp) {
                    // 如果大于的话，表名肯定是在low和hign的前半段
                    high = mid - 1;
                } else {
                    // 否则的话在后半段
                    low = mid + 1;
                }
            }
            // 如果最后low超过了high，也有可能是high自己递减小于了low,此时停止
            // 表明找到了要插入的位置,最后的话一定是low位置找到了，也就是最小的位置插入当前元素
            for (int j = i - 1; j >= low; j--) {
                nums[j + 1] = nums[j];
            }
            // low表示那个位置，表名low之后的元素都需要移动
            nums[low] = temp;
        }
    }

    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{8, 7, 5, 3, 2, 1};
        int[] nums = new int[]{12, 15, 9, 20, 6, 31};
        binaryInsertionSort(nums);
        System.out.print("[ ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }
}
