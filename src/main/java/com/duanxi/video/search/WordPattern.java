package com.duanxi.video.search;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author caoduanxi
 * @2019/12/14 21:08
 * leetcode 290 单词规律
 * <p>
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * <p>
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 * <p>
 * 示例1:
 * <p>
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class WordPattern {
    public static boolean wordPattern(String pattern, String str) {
        /**
         * 只有两种情况为false:
         * 1.键相同值不相同
         * 2.键不相同值相同
         */
        if (pattern == null || str == null) return false;
        String[] split = str.split(" ");
        if (pattern.length() != split.length) return false;
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        for (int i = 0; i < pattern.length(); i++) {
            if (!map.containsKey(pattern.charAt(i))) {
                // 键不相同，值相同
                // 还可以用containValues()判断是否值是否相同
                if (set.add(split[i])) {
                    map.put(pattern.charAt(i), split[i]);
                } else {
                    return false;
                }
            } else {
                // 键相同，值不是原来匹配的值
                if (!map.get(pattern.charAt(i)).equals(split[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(wordPattern("abba", "dog dog dog dog"));
        System.out.println(wordPattern("aba", "dog cat cat"));
    }
}
