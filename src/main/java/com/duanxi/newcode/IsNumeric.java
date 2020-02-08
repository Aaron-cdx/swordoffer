package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/8 9:53
 * 牛客网：表示数值的字符串
 * <p>
 * 题目:
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 */
public class IsNumeric {
    private int index = 0;

    public boolean isNumeric(char[] str) {
        // 数值中只能包含小数点
        // +100/5e2/-123/3.1416/-1E-16

        /**
         * 第一种方法解题：使用正则表达式匹配
         */
        /*if (str == null || str.length == 0) return false;
        String s = String.valueOf(str);
        return s.matches("[\\+\\-]?\\d*(\\.\\d+)?([eE][\\+\\-]?\\d+)?");*/
        /**
         * 第二种解法：参考剑指offer
         * 把握合法数值的规律
         * 如果不含有. E e 的话可能会含有+ - 这个时候只需要直接匹配数字到最后即可
         * 如果含有. 的话，此时前面有数字后面允许没有数字，或者前面没有数字后面需要有数字
         * 如果含有E e 的话，此时需要注意前后都需要有数字，否则是不合法字符
         */
        if (str.length < 1) return false;

        boolean flag = scanInteger(str);

        // 现在开始扫描小数点的情况
        if (index < str.length && (str[index] == '.')) {
            index++; // 跳过小数点,因为后面不能有+ -号，所以直接扫描整数部分
            // 此时需要结合前后来判断，如果'.'前后都没有数字，判定为false
            // 如果前面有数字或者后面有数字，都为true
            // 因为.3=0.3||0.=0.0都是合法数值
            flag = scanUnsignedInteger(str) || flag;
        }

        // 扫描eE部分
        if (index < str.length && (str[index] == 'e' || str[index] == 'E')) {
            index++;
            // 这个时候需要扫描整体
            // 如果遇到e E的时候，此时前后都需要含有数字 所以之间关系是一个与
            flag = flag && scanInteger(str);
        }
        // 最后的话，就是判断flag是否为真，以及index有没有走到最后
        return flag && index == str.length;
    }

    private boolean scanInteger(char[] str) {
        if (index < str.length && (str[index] == '+' || str[index] == '-')) {
            index++;
        }
        return scanUnsignedInteger(str);
    }

    private boolean scanUnsignedInteger(char[] str) {
        int start = index;
        // 单纯的扫描无符号的整数，此时是排除了+ -之后的数字
        while (index < str.length && (str[index] >= '0' && str[index] <= '9')) {
            index++;
        }
        // 判断是否存在整数
        return start < index;
    }


    public static void main(String[] args) {
        IsNumeric test = new IsNumeric();
//        boolean numeric = test.isNumeric("123.45e+6".toCharArray());
        boolean numeric = test.isNumeric("1.2.3".toCharArray());
        System.out.println(numeric);
    }
}
