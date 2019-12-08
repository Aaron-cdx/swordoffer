package com.duanxi.video.array;

/**
 * @author caoduanxi
 * @2019/12/8 20:11
 * leetcode 3 无重复字符的最长子串
 * <p>
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestSubstringWithoutRepeat {
    public static int lengthOfLongestSubstring1(String s) {
        int res = 0;
        if (s == null || s.length() == 0) return res;
        // 首先建立一个字符的数组 注意字符公有256个
        int[] arr = new int[256];// 0-255
        // 所有的都以a为准，arr['b'-'a']=arr[1]
        int l = 0;
        int r = 0;
        // 从0开始
        while (l < s.length() && r < s.length()) {
            // 表示没有重复的
            if (arr[s.charAt(r)] != 0) {
                arr[s.charAt(l++)] = 0;
            } else {
                arr[s.charAt(r++)] = 1;
            }
            res = Math.max(res, r - l);
        }
        return res;
    }

    public static int lengthOfLongestSubstring(String s) {
        int res = 0;
        if (s == null || s.length() == 0) return res;
        // 首先建立一个字符的数组
        int[] arr = new int[256];// 0-255
        int l = 0;
        int r = -1;
        while (l < s.length()) {
            // 与之前一样，这里只管+即可，所以每一次只需要判断r+1的位置是否为0即可
            if ((r + 1) < s.length() && arr[s.charAt(r + 1)] == 0) {
                arr[s.charAt(++r)] = 1;
            } else {
                arr[s.charAt(l++)] = 0;
            }
            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3 1 3 1
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring(" "));
    }
}





















