package com.duanxi.byteDance;

/**
 * @author caoduanxi
 * @Date 2020/2/18 18:27
 * 字节跳动:36进制的加法(要求不能转化为十进制)
 * <p>
 * 题目：
 * 36进制由0-9，a-z，共36个字符表示，最小为'0'
 * '0''9'对应十进制的09，'a''z'对应十进制的10-35
 * 例如：'1b' 换算成10进制等于 1 * 36^1 + 11 * 36^0 = 36 + 11 = 47
 * 要求按照加法规则计算出任意两个36进制正整数的和
 * 如：按照加法规则，计算'1b' + '2x' = '48'
 */
public class BaseAdditionOfThritySix {
    // 1b = 1*36^1 + 11*36^0 = 47
    // 2x = 2*36^1 + 33*36^0 = 105
    // 1b+2x = 152
    // 152/36 = 4...8
    // 最后结果为48

    /**
     * 转化为十进制的解法很简单
     */
    /*private String solve(String first, String second) {
        if (first == null || second == null) return "";
        // 主要是获取结果之后，转换的问题
        int sum = getNumber(first) + getNumber(second);
        StringBuilder res = new StringBuilder();
        char c;
        // 取余数获取当前尾数，取除数获取36个数
        while (sum != 0) {
            int temp = sum % 36;
            // 拼接大于10，也就是需要转化成 a b c d .....的
            if (temp >= 10) {
                c = (char) (temp - 10 + 'a');
            } else {
                c = (char) (temp + '0');
            }
            res.append(c);
            sum /= 36;
        }
        // 因为是从最后一位拼接的所以需要翻转
        return res.reverse().toString();
    }

    private int getNumber(String str) {
        // 获取长度
        int len = str.length();
        int number = 0;
        for (int i = 0; i < len; i++) {
            // 获取当前数字
            char c = str.charAt(i);
            // 非数字的话需要以a为基准转化成11....
            int temp;
            if (!Character.isDigit(c)) {
                temp = c - 'a' + 10;
            } else {
                temp = c - '0';
            }
            number += temp * (int) Math.pow(36, len - 1 - i);
        }
        return number;
    }*/

    /**
     * 不能转化为十进制的解法
     * 也很简单，就是两位相加，利用一个变量记录进位信息即可
     */
    public static int getIntFromChar(char c) {
        int number;
        if (c >= '0' && c <= '9') {
            number = c - '0';
        } else {
            number = c - 'a' + 10;
        }
        return number;
    }

    public static String solveTwoStringAdd(String first, String second) {
        StringBuilder res = new StringBuilder();
        String standard = "0123456789abcdefghijklmnopqrstuvwxyz";
        int i = first.length() - 1;
        int j = second.length() - 1;
        if (i < 0 || j < 0) {
            return "";
        }
        // 进位标志
        int temp = 0;
        while (i >= 0 && j >= 0) {
            int f = getIntFromChar(first.charAt(i));
            int s = getIntFromChar(second.charAt(j));
            int sum = f + s;
            if (sum >= 36) {
                int s1 = sum - 36;// 看看除去36之后余多少
                char c = standard.charAt(s1);
                res.append(c);
                temp = 1; // 有进位
            } else {
                int s1 = sum + temp;
                char c = standard.charAt(s1);
                res.append(c);
                temp = 0; // 肯定没有进位
            }
            i--;
            j--;
        }
        // 如果数字还没走完
        while (i >= 0) {
            int sum = getIntFromChar(first.charAt(i)) + temp;
            if (sum >= 36) {
                int s1 = sum - 36;// 看看除去36之后余多少
                char c = standard.charAt(s1);
                res.append(c);
                temp = 1; // 有进位
            } else {
                int s1 = sum + temp;
                char c = standard.charAt(s1);
                res.append(c);
                temp = 0; // 肯定没有进位
            }
            i--;
        }
        while (j >= 0) {
            int sum = getIntFromChar(second.charAt(j)) + temp;
            if (sum >= 36) {
                int s1 = sum - 36;// 看看除去36之后余多少
                char c = standard.charAt(s1);
                res.append(c);
                temp = 1; // 有进位
            } else {
                int s1 = sum + temp;
                char c = standard.charAt(s1);
                res.append(c);
                temp = 0; // 肯定没有进位
            }
            j--;
        }
        if (temp != 0) {
            res.append("1");
        }
        return res.reverse().toString();
    }


    public static void main(String[] args) {
        BaseAdditionOfThritySix test = new BaseAdditionOfThritySix();
        /*System.out.println(test.getNumber("1b"));
        System.out.println(test.getNumber("2x"));
        System.out.println(test.getNumber("48"));
        System.out.println(test.solve("1b", "2x"));
        System.out.println(test.solve("a2", "33"));*/
        System.out.println(test.solveTwoStringAdd("a2", "33"));
    }
}
