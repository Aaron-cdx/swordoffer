package com.duanxi.byteDance;

/**
 * @author caoduanxi
 * @Date 2020/3/4 21:31
 */
public class FindNumsAppearOnce {
    public void findNumsAppearOnce(int[] array, int[] nums1, int[] nums2) {
        int n = array.length;
        if (n == 2) {
            nums1[0] = array[0];
            nums2[0] = array[1];
        }
        int res = array[0];
        for (int i = 1; i < array.length; i++) {
            res ^= array[i];
        }
        // 异或最后的结果就是两个数的补位,只要将两个数分开摆放即可
        int index = findFirstBitOfOneIndex(res);
        // 这里获取index的时候就是将两个数的不同的位分开计算!
        for (int i = 0; i < array.length; i++) {
            if (isSameBit(array[i], index)) {
                nums1[0] ^= array[i];
            } else {
                nums2[0] ^= array[i];
            }
        }
    }

    private boolean isSameBit(int num, int index) {
        return ((num >> index) & 1) == 1;
    }

    private int findFirstBitOfOneIndex(int num) {
        int index = 0;
        while ((num & 1) == 0 && index < 32) {
            num >>= 1;
            index++;
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println(4 ^ 5);
    }
}
