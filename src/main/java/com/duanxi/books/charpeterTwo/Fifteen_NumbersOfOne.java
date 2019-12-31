package com.duanxi.books.charpeterTwo;

/**
 * @author caoduanxi
 * @Date 2019/12/31 13:29
 * 剑指offer 面试题15 二进制数字中1的个数
 * <p>
 * 题目：请实现一个函数，输入一个整数，输出该数二进制表示中1的个数。
 * 例如把9表示成二进制位1001，有两位是1，因此输入9，答案输出为1
 * <p>
 * 主要考查&运算符的操作，移位操作>> << >>>
 */
public class Fifteen_NumbersOfOne {

    /**
     * 关于正负数的二进制
     * 正数的二进制也称为原码，正数的补码就是其原码
     * 而负数的补码是其正数源码的按位取反之后+1操作，两个1相加为0，但是进1，如果第一位越界则移除
     */
    // 如果是负数会造成死循环，因为负数无论怎么移动，右边一定会补1
    public static int numberOfOne1(int num) {
        int count = 0;
        while (num != 0) {
            if ((1 & num) != 0) {
                count++;
            }
            num = num >> 1;
        }
        return count;
    }

    // 这是比较每一位，复杂度比较高，一般32位的话，需要比较32次
    public static int numberOfOne(int num) {
        int flag = 1;
        int count = 0;
        while (flag != 0) {
            if ((flag & num) != 0)
                count++;
            flag <<= 1;
        }
        return count;
    }

    // 有几个比较几次
    public static int numberOfOne2(int num) {
        int count = 0;
        while (num != 0) {
            ++count;
            num = num & (num - 1);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(numberOfOne(-7));
    }
}
