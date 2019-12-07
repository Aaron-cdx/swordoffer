package com.duanxi.books.charpeterTwo;

/**
 * @author caoduanxi
 * @2019/12/7 21:15
 * 复习String  StringBuilder  StringBuffer
 * String一般用于较短的字符串创建，但是String只要有改动就会生成新的对象，会重新开辟内存空间，对于内存空间是一种浪费
 * 使用StringBuilder或者StringBuffer来代替
 * <p>
 * StringBuilder一般单线程的时候使用StringBuilder，执行速度很快，线程不安全
 * StringBuffer一般多线程的时候使用，执行速度慢，但是线程安全
 * <p>
 * 操作少量的数据用String
 * 多线程操作字符串缓冲区下操作大量数据StringBuffer
 * 单线程操作字符串缓冲区下操作大量数据StringBuilder
 * <p>
 * 面试题5  替换空格
 * <p>
 * 题目：
 * 请实现一个函数，把字符串中的每个空格替换成"%20",例如，输入"We are happy.",
 * 则输出"We%20are%20happy."
 */
public class Fifth_ReplaceBlank {

    public static String ReplaceBlank1(String s) {
        String replace = s.replace(" ", "%20");
        return replace;
    }

    // 暴力法,采用字符拼接的方法O(n^2)?
    // 这里主要是移动了n个地方，所以对于n个字符移动n，时间复杂度就是O(n^2)
    public static String ReplaceBlank2(String s) {
        if (s == null || s.length() == 0) return null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i) == ' ' ? "%20" : s.charAt(i));
        }
        return sb.toString();
    }
    // 时间复杂度O(n)

    /**
     * 这里的时间复杂度，是通过先计算空格的数量，对于空间进行扩展之后
     * 利用双指针，采用赋值的方式进行，但是这样开辟了新的空间，不过上面的暴力法也开辟了新的空间
     * 时间复杂度确实是O(n)，双指针一路赋值，遇到空格，只需要赋值3次，填充字符即可
     */
    public static String ReplaceBlank(String s) {
        StringBuffer sb = new StringBuffer(s);
        if (s == null || s.length() == 0) return null;
        // 遍历一遍获取替换后的长度
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') count++;
        }
        // 获取到的扩展长度
        int len = s.length() + 2 * count;
        // 获取到原始字符串的最后一个下标
        int p = sb.length() - 1;
        sb.setLength(len);
        // 扩容之后的字符串的最后一个下标
        int q = len - 1;

        while (p >= 0 && q >= 0) {
            if (s.charAt(p) == ' ') {
                sb.setCharAt(q--, '0');
                sb.setCharAt(q--, '2');
                sb.setCharAt(q--, '%');
                // 这里注意p也要往前走
                p--;
            } else {
                sb.setCharAt(q--, s.charAt(p--));
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(ReplaceBlank("We are happy."));
    }
}
