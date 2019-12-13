package com.duanxi.video.search;

/**
 * @author caoduanxi
 * @2019/12/13 18:55
 * leetcode 242 有效的字母异位词
 * <p>
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidAgaram {
    // 第一种世家你复杂度O(n) 空间复杂度O(2n) 虽然实际为O(n)
    public boolean isAnagram1(String s, String t) {
        if(s.length() != t.length()) return false;

        int[] arr = new int[26];
        int[] arr1 = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i)-'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            arr1[t.charAt(i)-'a']++;
        }
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != arr1[i])
                return false;
        }
        return true;
    }
    // 可以利用一个数组做到真的O(n)的空间复杂度
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;

        int[] arr = new int[26];
        // 一个负责加一个负责减法
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i)-'a']++;
            arr[t.charAt(i)-'a']--;
        }
        for (int num : arr) {
            if(num != 0)
                return false;
        }

        return true;
    }
}
