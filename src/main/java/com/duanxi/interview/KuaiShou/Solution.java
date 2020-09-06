package com.duanxi.interview.KuaiShou;

/**
 * @author caoduanxi
 * @Date 2020/8/31 13:46
 * leetcode 76、最小覆盖子串
 * 输入ADOBECODEBANC
 * ABC
 * 最小覆盖的就是BAC
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(s);
        System.out.println(s.length());
    }
    public String minWindow(String s, String t) {
        String res = s;
        int[] memo = new int[256];
        int[] window = new int[256];
        for (int i = 0; i < t.length(); i++) {
            memo[t.charAt(i)]++;
        }
        int valid = 0;
        for (int i : memo) {
            if (i != 0) {
                valid += 1;
            }
        }
        // 此时固定滑动窗口
        int l = 0;
        int r = 0;
        // 注意滑动窗口的滑动，是先保证r向前走，l用作收缩的，但是注意window中的数目需要减少
        int match = 0;
        boolean flag = false;
        while (r < s.length()) {
            char c = s.charAt(r);
            if (memo[c] != 0) {
                window[c]++;
                if (window[c] == memo[c]) {
                    match++;
                }
            }
            r++;
            // 如果相等了此时需要进入循环判断了
            while (match == valid) {
                flag = true;// 表名匹配到了
                // 此时需要判断长度是否小于当前的，是的话赋值，然后向后走
                res = res.length() <= (r - l) ? res : s.substring(l, r);
                // 此时需要将当前区间的窗口向后移动
                char c1 = s.charAt(l);
                if (memo[c1] != 0) {
                    if (window[c1] == memo[c1]) {
                        match--;
                    }
                    window[c1]--;
                }
                l++;
            }
        }
        return flag ? res : "";
    }
}
