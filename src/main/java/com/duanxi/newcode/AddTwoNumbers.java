package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/7 10:50
 * 牛客网：不用加减乘除做加法
 * <p>
 * 题目：
 * 写一个函数，求两个整数之和，
 * 要求在函数体内不得使用+、-、*、/四则运算符号。
 */
public class AddTwoNumbers {
    /**
     * 异或操作：求相加，但是不管进位
     * 与操作：求相与操作，判断是否有进位
     * 然后将异或操作和与操作的值相加即可获得总的和
     */
    public int Add(int num1, int num2) {
        // 使用num2作为进制位 异或操作只管计算，不管进位
        while (num2 != 0) {
            int temp = num1 ^ num2;
            // 如果num2 != 0 表示出现了进位
            num2 = (num1 & num2) << 1;
            num1 = temp;
        }
        return num1;
    }

    public static void main(String[] args) {
        AddTwoNumbers test = new AddTwoNumbers();
        int add = test.Add(111, 899);
        int add1 = test.Add(5, 7);
        System.out.println(add);
    }
}
