package com.duanxi.video.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caoduanxi
 * @2019/12/9 20:01
 * leetcode 438 找到字符串中所有的字母异位词
 * <p>
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * <p>
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 * <p>
 * 说明：
 * <p>
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 * <p>
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * <p>
 * 输出:
 * [0, 6]
 * <p>
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindAnagramsInString {
    /**
     * 注意，这里使用的Integer是一个对象，大于127以后不从常量池拿，在127之前都是从常量池直接获取
     * 当128及以后都是直接new一个对象出来，此时比较需要使用equals，因为是对象的比较
     */
    private static List<Integer> findAnagrams1(String s, String p) {
        List<Integer> list = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        // 放入所有的字符
        for (int i = 0; i < p.length(); i++) {
            map.put(p.charAt(i), map.getOrDefault(p.charAt(i), 0) + 1);
        }
        // 用一个指标表示起始位置
        int l = 0;
        int r = 0;
        int match = 0;
        // 表示真实的计数，如果到某一个数字两个值相等，则记录下第一个start的位置，否则直接到当时的位置
        // 这是遍历整个字符串s
        while (r < s.length()) {
            char c = s.charAt(r);
            if (map.containsKey(c)) {
                // 获取值，如果没有就直接为0,然后+1
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(map.get(c))) {
                    match++;
                }
            }
            r++; // 表示一直向前走，知道匹配的数字为p.length()表示匹配到了
            // 如果匹配到这么多字符的话
            while (match == map.size()) {
                // 判断一下是否是连续的
                if ((r - l) == p.length()) {
                    // 将左边窗口的边界放入
                    list.add(l);
                }
                // 放入之后，此时添加了一个，为了与边上的密切结合，此时从l开始减去window中存储的数目
                char c1 = s.charAt(l);
                if (map.containsKey(c1)) {
                    // 判断数目是否相等，数目相等的话，就直接收缩有效的滑动窗口，否则对于无效的滑动窗口采用紧逼的形式靠近r
                    if (window.get(c1).equals(map.get(c1))) {
                        // 匹配数-1
                        match--;
                    }
                    // 窗口中这个字符-1,缩小范围
                    window.put(c1, window.get(c1) - 1);
                }
                l++;// l逐渐逼近即将查找到的那个数
            }
        }
        return list;
    }

    // 利用数组来优化这道题
    private static List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int[] memo = new int[26];
        int[] window = new int[26];
        for (int i = 0; i < p.length(); i++) {
            memo[p.charAt(i) - 'a']++;
        }
        // 防止出现aa这种重复的，只计算匹配1次即可
        int len = 0;// 有效的长度
        for (int i : memo) {
            if (i != 0) {
                len++;
            }
        }
        // 设置左右边界
        int l = 0;
        int r = 0;
        int match = 0;
        while (r < s.length()) {
            char c = s.charAt(r);
            if (memo[c - 'a'] != 0) {
                window[c - 'a']++;
                if (window[c - 'a'] == memo[c - 'a']) {
                    match++;
                }
            }
            r++;
            // 怎么判断数组的有效长度，遍历一次memo数组即可
            while (match == len) {
                if ((r - l) == p.length()) {
                    list.add(l);
                }
                char c1 = s.charAt(l);
                if (memo[c1 - 'a'] != 0) {
                    if (window[c1 - 'a'] == memo[c1 - 'a']) {
                        match--;
                    }
                    window[c1 - 'a']--;
                }
                l++;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println(findAnagrams("baa", "aa"));
    }
}
































