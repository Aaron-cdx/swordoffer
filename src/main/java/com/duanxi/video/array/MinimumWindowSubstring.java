package com.duanxi.video.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caoduanxi
 * @2019/12/10 20:30
 * leetcode 76 最小覆盖子串
 * <p>
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 * <p>
 * 示例：
 * <p>
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * <p>
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimumWindowSubstring {
    public static String minWindow1(String s, String t) {
        // 利用Map结构，找到这个字符串所有包含然后再说
        Map<Character, Integer> memo = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        // 获取t字符串的那些字母
        for (int i = 0; i < t.length(); i++) {
            memo.put(t.charAt(i), memo.getOrDefault(t.charAt(i), 0) + 1);
        }
        int l = 0;
        int r = 0;
        // 匹配
        int match = 0;
        boolean flag = false;
        String res = s;
        while (r < s.length()) {
            char c1 = s.charAt(r);
            if (memo.containsKey(c1)) {
                window.put(c1, window.getOrDefault(c1, 0) + 1);
                // 防止二次匹配多个所以限定数目一定相等
                if (window.get(c1).equals(memo.get(c1))) {
                    match++;
                }
            }
            r++;
            // 如果匹配的行值相等的话，一直循环
            while (match == memo.size()) {
                flag = true;// 证明匹配到过
                // 这里需要赋值操作
                res = res.length() >= (r - l) ? s.substring(l, r) : res;
                char c = s.charAt(l);
                if (memo.containsKey(c)) {
                    // 如果数目相等
                    if (window.get(c).equals(memo.get(c))) {
                        match--;
                    }
                    window.put(c, window.get(c) - 1);
                }
                l++;
            }
        }
        return flag ? res : "";
    }

    // 利用数组实现
    public static String minWindow(String s, String t) {
        String res = s;
        int[] memo = new int[256];
        int[] window = new int[256];
        for (int i = 0; i < t.length(); i++) {
            memo[t.charAt(i)]++;
        }
        // 窗口左边l，右边r
        int l = 0;
        int r = 0;
        int match = 0;
        // 主要用于判断是否有这个子串存在，用于判断输出""还是res
        boolean flag = false;
        // 需要判断一下memo中有效的字符个数
        int valid = 0;
        for (int num : memo) {
            if (num != 0) {
                valid++;
            }
        }
        // 对整个数据进行遍历
        while (r < s.length()) {
            char c = s.charAt(r);
            // 表明当前字符在t中存在
            if (memo[c] != 0) {
                // 添加到窗口window中
                window[c]++;
                // 添加完之后判断窗口window中当前字符的个数是否等于memo存储的数组中当前的字符
                if (window[c] == memo[c]) {
                    // 是的话表明当前有字符匹配上了
                    match++;
                }
            }
            r++;// 向前走
            // 判断match 是否和 memo中的有效字符个数相同
            // 相同的话进入循环，否则继续上面的循环
            while (match == valid) {
                // 表明匹配上了，此时flag=true
                flag = true;
                // 记录最短的字符串res,长度大于原长度的话，则取原长度
                // 否则获取l...r之间的字符串
                res = res.length() >= (r - l) ? s.substring(l, r) : res;
                // 将当前左窗口的字符取出来
                char c1 = s.charAt(l);
                // 如果memo中含有当前值
                if (memo[c1] != 0) {
                    // 继续判断字符数量是否与window中的相等
                    if (memo[c1] == window[c1]) {
                        match--;// 减少一个匹配的字符
                    }
                    // window减少一个有效字符，因为l在往前走
                    window[c1]--;
                }
                // l前进，缩小窗口
                l++;
            }
        }
        // 只要flag=true就表示出现过，返回res，否则返回""
        return flag ? res : "";
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
//        System.out.println(minWindow("ask_not_what_your_country_can_do_for_you_ask_what_you_can_do_for_your_country", "ask_country"));
//        System.out.println(minWindow("a", "a"));
    }
}
