package com.duanxi.video.array;

/**
 * @author caoduanxi
 * @2019/12/7 18:47
 * leetcode 125 验证回文串
 * <p>
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidPalindrome {
    // 还是使用对撞指针来解决问题
    public static boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        // ".," 当循环之后也是true，其实是false
        while (l < r) {
            // 如果两者都为数字或者字母的话,但凡不等，直接返回false
            // 这个可以将不是字母或者字符的两个一直向前走，如果不满足小于就终止
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;
            if (Character.toLowerCase(s.charAt(l)) - Character.toLowerCase(s.charAt(r)) != 0) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public static boolean isPalindrome2(String s) {
        for (int i = 0, j = s.length() - 1; i < j; ) {
            char ic = s.charAt(i);
            char jc = s.charAt(j);
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                ic += ' ';
            }
            if (s.charAt(j) >= 'A' && s.charAt(j) <= 'Z') {
                jc += ' ';
            }
            if (!(ic >= 'a' && ic <= 'z' || ic >= '0' && ic <= '9')) {
                i++;
                continue;
            }
            if (!(jc >= 'a' && jc <= 'z' || jc >= '0' && jc <= '9')) {
                j--;
                continue;
            }
            if (jc != ic) return false;
            i++;
            j--;
        }
        return true;
    }
    // 不考虑.,，所以是true
    public static boolean isPalindrome3(String s) {
        if (s == null) return true;
        s = s.toLowerCase();
        int l = s.length();
        StringBuilder str = new StringBuilder(l);
        for (char c : s.toCharArray()) {
            if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')) {
                str.append(c);
            }
        }
        return str.toString().equals(str.reverse().toString());
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome3(".,"));
    }
}
