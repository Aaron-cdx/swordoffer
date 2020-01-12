package com.duanxi.newcode;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2020/1/10 19:40
 */
public class MoreThanHalfNum {
    public int MoreThanHalfNum_Solution(int[] array) {
//        if (array == null || array.length == 0) {
//            return 0;
//        }
//        Arrays.sort(array);
//        int res = 0;
//
//        for (int i = 0; i < array.length; i++) {
//            int count = 0;
//            int j = i;
//            while (j < array.length) {
//                if (array[j] == array[i]) {
//                    count++;
//                    j++;
//                }else{
//                    break;
//                }
//            }
//            if (count >= (array.length / 2)) {
//                res = array[i];
//            }
//            i = j-1;
//        }
//        return res;
        // 这是第二种方法，第二种方法比较机智，就是利用这个数一定大于一般，只要最后有数，就一定是这个数
        // 因为他的数量大于一半
        int n = array.length;
        if (n == 0) return 0;

        int num = array[0], count = 1;
        for (int i = 1; i < n; i++) {
            if (array[i] == num) count++;
            else count--;
            if (count == 0) {
                num = array[i];
                count = 1;
            }
        }
        // Verifying
        count = 0;
        for (int i = 0; i < n; i++) {
            if (array[i] == num) count++;
        }
        if (count * 2 > n) return num;
        return 0;
    }

    public static void main(String[] args) {
        MoreThanHalfNum num = new MoreThanHalfNum();
        System.out.println(num.MoreThanHalfNum_Solution(new int[]{1, 2, 3, 4, 2, 2, 2, 5, 1}));
        System.out.println(num.MoreThanHalfNum_Solution(new int[]{2,1,2,3,2,4,2,5,2,6,2}));
    }
}
