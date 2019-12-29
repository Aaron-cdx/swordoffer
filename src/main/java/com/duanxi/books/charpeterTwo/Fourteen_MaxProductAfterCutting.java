package com.duanxi.books.charpeterTwo;

/**
 * @author caoduanxi
 * @Date 2019/12/29 22:10
 * 剑指offer 面试题14 剪绳子
 * <p>
 * 题目：给你一根长度为n的绳子，请把绳子剪成m段，(m,n)都是整数且都大于1，
 * 每段绳子长度记为k[0],k[1],...k[m]。请问k[0]*k[1]....*k[m]可能的最大乘积是多少？
 * 例如当绳子长度为8时，此时分为2,3,3,最大乘积为18
 */
public class Fourteen_MaxProductAfterCutting {
    // 这里是先计算出最原始的，从下往上递推
    public static int maxProductAfterCutting1(int length) {
        if (length < 2) return 0;
        if (length == 2) return 1;
        if (length == 3) return 2;
        int[] product = new int[length + 1];
        product[0] = 0;
        product[1] = 1;
        product[2] = 2;
        // 4 -> 1*3
        product[3] = 3;

        for (int i = 4; i <= length; i++) {
            int max = 0;
            // 以中间为界即可
            for (int j = 1; j <= i / 2; j++) {
                int multi = product[j] * product[i - j];
                max = Math.max(multi, max);
            }
            product[i] = max;
        }
        return product[length];
    }

    // 尽量切分3这个数字，贪婪算法的话需要数学公式来证明
    public static int maxProductAfterCutting(int length) {
        if (length < 2) return 0;
        if (length == 2) return 1;
        if (length == 3) return 2;

        int cutBlocks = length / 3;
        // 这种情况一定会切出一份4
        // 4的情况切分成两段2才是最好的 4 > 1*3
        if (length - cutBlocks * 3 == 1) {
            // 此时就减去一份切出的3
            cutBlocks -= 1;
        }
        // 这是在计算2的个数，如果是出现了1，则两个2，如果没有出现1，则表明只有一个2
//        int cutBlocks2 = (length - 3*cutBlocks)/2;
        // %3只有两种结果一种是1一种是2
        return (int) Math.pow(3, cutBlocks) * (int) Math.pow(2, length % 3 == 1 ? 2 : 1);
    }

    public static void main(String[] args) {
        System.out.println(maxProductAfterCutting(8));
    }
}
