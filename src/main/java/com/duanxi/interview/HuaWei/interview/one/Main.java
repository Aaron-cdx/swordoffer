package com.duanxi.interview.HuaWei.interview.one;

/**
 * @Author: caoduanxi
 * @Date: 2020/9/8
 * 两个字符串的乘积
 * "12" "34"
 * 思路1是最常规的方法，即相乘再相加，但是如果数字足够大的话，其实越界了！
 * 思路2
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
//        System.out.println(123 * 340);
//        System.out.println(123 * 40 + 123 * 300);
//        System.out.println(main.getRes("125", 30));
//        System.out.println(main.getAddRes("0", "4587"));
//        System.out.println(main.multiply("123","340"));
//        System.out.println(main.multiply("123","456"));
//        System.out.println(main.multiply("2","3"));
//        System.out.println(main.multiply("98", "9"));
        /* "5423396"
        "5424012638" */
        System.out.println(main.multiply("5423396", "5424012638"));
    }
    /*
    123
     340

     123 * 0
     123 * 40
     123 * 300
     */

    /**
     * 主要的思路是先相乘再相加
     */
    public String multiply1(String str1, String str2) {
        String res = "0";
        int len = str2.length();
        // 这里需要获取到当前的一个数字，然后将当前的数字转换为乘积，然后累加
        for (int i = len - 1; i >= 0; i--) {
            int c = str2.charAt(i) - '0';
            long num = (int) Math.pow(10, len - 1 - i) * c;
            res = getAddRes(res, getRes(str1, num));
        }
        return res;
    }

    /**
     * 计算相乘的结果
     */
    public String getRes(String str, long num) {
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        if (num == 0) return "0";
        if (str.equals("0")) return "0";
        long cur = 0;
        // 处理0的情况的
        if (num % 10 == 0) {
            while (num / 10 != 0) {
                sb.append("0");
                num /= 10;
            }
        }
        // 正式计算了
        for (int i = length - 1; i >= 0; i--) {
            int c = str.charAt(i) - '0';
            long tmp = c * num + cur;
            cur = tmp / 10;
            sb.append(tmp % 10);
        }
        // 需要考虑当前的进位
        if (cur > 0) {
            sb.append(cur);
        }
        return sb.reverse().toString();
    }

    /**
     * 计算相加结果
     */
    public String getAddRes(String str1, String str2) {
        // 按照最短的长度计算，太长了会产生越界的情况
        StringBuilder sb = new StringBuilder();
        int len = Math.min(str1.length(), str2.length()) - 1;
        int l = 0;
        int l1 = str1.length() - 1;
        int l2 = str2.length() - 1;
        long cur = 0; // 进位记录
        while (l <= l1 && l <= l2) {
            int c1 = str1.charAt(l1 - l) - '0';
            int c2 = str2.charAt(l2 - l) - '0';
            long sum = c1 + c2 + cur;
            sb.append(sum % 10);
            cur = sum / 10;
            l++;
        }
        while (l <= l1) {
            int c1 = str1.charAt(l1 - l) - '0';
            long sum = c1 + cur;
            sb.append(sum % 10);
            cur = sum / 10;
            l++;
        }
        while (l <= l2) {
            int c1 = str2.charAt(l2 - l) - '0';
            long sum = c1 + cur;
            sb.append(sum % 10);
            cur = sum / 10;
            l++;
        }
        if (cur > 0) {
            sb.append(cur);
        }
        return sb.reverse().toString();
    }

    // /////////////////////////////////////////////思路2\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public String multiply(String str1, String str2) {
        // 9*9 = 81即两个1位数的数相乘，结果不会超过两位数
        char[] value = new char[str1.length() + str2.length()];
        // 然后计算每一位的乘积
        int l1 = str1.length();
        int l2 = str2.length();
        for (int i = l1 - 1; i >= 0; i--) {
            for (int j = l2 - 1; j >= 0; j--) {
                // 用+=转换成字符
                value[i + j + 1] += (str1.charAt(i) - '0') * (str2.charAt(j) - '0');
            }
        }
        int carry = 0;
        for (int i = value.length - 1; i >= 0; i--) {
            value[i] += carry;
            carry = value[i] / 10;
            value[i] %= 10;
        }
        // 找到最开始的位置
        int beginIndex = 0;
        while(beginIndex <= value.length - 1 && value[beginIndex] == 0){
            beginIndex++;
        }
        // 这是出现了第一位的位置，然后需要依次递增十位百位千位
        for (int i = beginIndex; i < value.length; i++) {
            value[i] += '0';// 将其中的数字做一个转换，否则不识别
        }
        return new String(value,beginIndex,value.length-beginIndex);
    }
}
