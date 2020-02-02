package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/2 11:40
 * 牛客网：丑数
 * <p>
 * 题目：
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，
 * 但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 * <p>
 * 思路：
 * 按照动态规划，构建出所有的index指定大小范围内的丑数，最后返回即可。
 * 主要依据的是第一个丑数是1
 */
public class UglyNumber {
    public int GetUglyNumber_Solution(int index) {
        if (index < 7) return index;
        int res[] = new int[index];
        // 表示第一个
        res[0] = 1;
        int two = 0;
        int three = 0;
        int five = 0;
        // 这里是想从1开始网上累加，满足就存储
        for (int i = 1; i < res.length; i++) {
            // 需要获取到res中存储的结果，从小到大
            // 注意res[0]=1
            res[i] = Math.min(res[two] * 2, Math.min(res[three] * 3, res[five] * 5));
            // 用res[0]做铺垫
            if (res[i] == res[two] * 2) two++;
            if (res[i] == res[three] * 3) three++;
            if (res[i] == res[five] * 5) five++;
        }
        return res[index - 1];
    }

    /**
     * 判断一个数是否是丑数
     * 如何判断是否是整除，这个才是关键
     * 首先判断哪些是丑数
     * 首先除以2，看能否被整除，知道不能整除为止
     * 然后除以5，看能否被整除，直到不能整除为止
     * 最后除以3，看能否被整除，直到不能整除为止
     *
     * @param number
     * @return
     */
    public static boolean isUglyNumber(int number) {
        if (number % 2 == 0) {
            if (number / 2 == 1) {
                return true;
            } else {
                // 这个能确保/出来的是一个整数
                return isUglyNumber(number / 2);
            }
        }
        if (number % 5 == 0) {
            if (number / 5 == 1) {
                return true;
            } else {
                // 这个能确保/出来的是一个整数
                return isUglyNumber(number / 5);
            }
        }
        if (number % 3 == 0) {
            if (number / 3 == 1) {
                return true;
            } else {
                // 这个能确保/出来的是一个整数
                return isUglyNumber(number / 3);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isUglyNumber(15));
        System.out.println(isUglyNumber(6));
        System.out.println(isUglyNumber(9));
        System.out.println(isUglyNumber(7));
        System.out.println(isUglyNumber(14));
        System.out.println(6 % 2);
    }
}
