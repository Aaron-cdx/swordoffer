package com.duanxi.sortingAlgorithm;

/**
 * @author caoduanxi
 * @Date 2019/12/19 19:30
 * 排序-堆排序
 * <p>
 * 思想：完全二叉树的插入，大根堆的构造，交换之后的调整，堆排序三部曲
 * <p>
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 */
public class HeapSort {
    // 左孩子：2*i + 1
    // 右孩子：2*i + 2
    // 一个节点的父节点： (i-1)/2
    public static void heapSort(int[] nums) {
        if (nums == null || nums.length < 2) return;
        for (int i = 0; i < nums.length; i++) {
            heapInsert(nums, i);
        }
        int size = nums.length;
        // 由于是大根堆，最大的数肯定是在第一个
        // 交换第一个数
        swap(nums, 0, --size);
        // 大根堆的环境被破坏了，此时需要调整
        while (size > 0) {
            // 调整完以后，此时又恢复大根堆了，此时最大值又在0位置，除去排好的size那个位置
            heapify(nums, 0, size);
            // 交换，破坏大根堆
            swap(nums, 0, --size);
        }
    }

    private static void heapify(int[] nums, int index, int size) {
        // 找到自己的左孩子
        int left = 2 * index + 1;
        // 循环遍历调整，确保不能越界,这里已经确保在size-1的地方了
        while (left < size) {
            // 寻找最大的下标 left+1表示右孩子节点,确保不能越界
            int largest = ((left + 1) < size && nums[left + 1] > nums[left]) ? left + 1 : left;
            // 如果自己就是最大的，停止循环
            largest = nums[largest] > nums[index] ? largest : index;
            if (largest == index) {
                break;
            }
            // 否则进行一个调整，进行一个交换
            swap(nums, largest, index);
            index = largest;// 将index移动到这个最大值的地方，因为不能确保下面的值是否还有比自己大的
            left = 2 * index + 1;// 利用新的index重新计算左孩子的坐标
        }
    }

    private static void heapInsert(int[] nums, int index) {
        // 查找到自己的父节点
//        int p = (index - 1) >> 1;
        // 要一直查找到比自己父节点小为止,如果大于自己的父节点，就需要交换
        while (nums[index] > nums[(index - 1) / 2]) {
            swap(nums, index, (index - 1) / 2);
            index = (index - 1) / 2;
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
        heapSort(nums);
        System.out.print("[ ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }
}
