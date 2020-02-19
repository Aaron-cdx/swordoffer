package com.duanxi.byteDance;

/**
 * @author caoduanxi
 * @Date 2020/2/19 9:33
 * 字节跳动面试：翻转URL
 * <p>
 * 题目：
 * 将www.bytedance.com 翻转成 com.bytedance.www
 */
public class ReverseURL {
    /**
     * 采用两次翻转的方法
     * 第一次翻转是为了让所有的单词都前后交换。保证顺序
     * 第二次翻转是为了让所有的单词都变为正常的顺序。保证结果正确性。
     */
    private String reverseURL(String url) {
        char[] chars = url.toCharArray();
        // 第一次翻转，使得前后翻转，此时每段的网址都是翻转后的
        swap(chars, 0, chars.length - 1);
        int i = 0;
        int j = 0;
        while (i < chars.length) {
            if (chars[j] == '.') {
                // 到达了第一个段的分界点,执行翻转
                swap(chars, i, j - 1);
                // i直接定位到j后面的字母
                i = j + 1;
            }
            // 到达了最后这里，直接翻转结束
            if (j == chars.length - 1) {
                swap(chars, i, j);
                break;
            }
            j++;
        }
        return String.valueOf(chars);
    }

    private void swap(char[] chars, int i, int j) {
        while (i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        ReverseURL test = new ReverseURL();
        System.out.println(test.reverseURL("www.toutiao.com"));
        System.out.println(test.reverseURL("www.toutiao.cn"));
    }

}
