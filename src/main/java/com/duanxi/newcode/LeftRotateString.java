package com.duanxi.newcode;


/**
 * @author caoduanxi
 * @Date 2020/2/6 12:14
 * 牛客网：左旋转字符串
 * <p>
 * 题目：
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，
 * 就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，
 * 请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,
 * 要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
 */
public class LeftRotateString {
    public String LeftRotateString(String str, int n) {
        /**
         * 最秀的解法：交换3次元素的位置
         * 第一次交换n前面元素的位置
         * 第二次交换n后面元素的位置
         * 第三次交换所有元素的位置
         * YX=(X^TY^T)^T  X的翻转 Y的翻转 最后总的翻转
         */
        char[] chars = str.toCharArray();
        int len = str.length();
        if (len == 0) return str;
        n %= len;
        // abcdefg ==> bacdefg
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            swap(chars, i, j);
        }
        // bacdefg ==> bagfedc
        for (int i = n, j = len - 1; i < j; i++, j--) {
            swap(chars, i, j);
        }
        // bagfedc ==> cdefgab
        for (int i = 0, j = len - 1; i < j; i++, j--) {
            swap(chars, i, j);
        }
        return String.valueOf(chars);
        /*for (int i = 0; i < n; i++) {
            char temp = chars[i];
            // 应该移动到当前位置的是
//            chars[i] = chars[i + n];
            // 应该移动到chars[i+n]位置的是
            // 这里一步比较重要，出现循环的位置处在这
            int j = i;
            while (j + n < len) {
                chars[j] = chars[j + n];
                j += n;
            }
            chars[i + len - n] = temp;
        }
        return String.valueOf(chars);*/

        /**
         * 网上的一种思路：这种思路合并然后截取，因为合并两个之后，相当于左移了len位
         * 只需要截取所需要的n len即可
         *
         * 但是这种做法的意义不大，如果是笔试题可以通过，但是面试的话绝对不可行！
         */
        /*int len = str.length();
        if(len == 0) return "";
        n = n%len;
        str += str;
        return str.substring(n,n+len);*/
    }

    private void swap(char[] chars, int a, int b) {
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }

    public static void main(String[] args) {
        LeftRotateString test = new LeftRotateString();
//        String string = test.LeftRotateString("abcXYZdef", 3);
        String string1 = test.LeftRotateString("abcdefg", 2);
//        System.out.println(string);
        System.out.println(string1);
    }
}
