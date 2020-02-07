package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/7 14:24
 * 牛客网：构建乘积数组
 * <p>
 * 题目：
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 */
public class BuildMultiplyArray {
    public int[] multiply(int[] A) {
        if (A == null) return null;
        // 这里要获取的是计算出B[i]
        // 是不包含A[i]这个元素的A中所有元素的乘积
        /**
         * 第一种方法
         */
        /*int[] B = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = getMulti(A, i);
        }*/

        /**
         * 第二种方法：使用B[i]记录没有A[i]乘积的结果值
         * 使用temp记录A[i]之后的乘积的结果值
         *
         * 每每相乘的时候，只要避开那一个i的值即可
         * B[i]中第一次遍历正确的值只有最后一个。因为没有取到A[i]的值
         * 都是取到了A[i-1]
         */
        int[] B = new int[A.length];
        B[0] = 1;
        // 这里需要判断
        for (int i = 1; i < A.length; i++) {
            B[i] = B[i - 1] * A[i - 1];
        }
        // 用B[i]记录没有A[i]的乘积
        // 用temp记录A[i]之后的乘积
        // 第二重循环的话，需要避开i这个节点的值
        int temp = 1;
        for (int i = A.length - 2; i >= 0; i--) {
            // temp记录的是当前i之后的乘积大小
            temp *= A[i + 1];
            B[i] *= temp;
        }
        return B;
    }

    /**
     * 第一种方法几乎所有人都会写，但是吧时间复杂度太高了
     */
    public int getMulti(int[] A, int index) {
        int res = 1;
        for (int i = 0; i < A.length; i++) {
            if (i != index) {
                res *= A[i];
            }
        }
        return res;
    }
}
