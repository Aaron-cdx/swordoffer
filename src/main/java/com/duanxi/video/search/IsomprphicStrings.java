package com.duanxi.video.search;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author caoduanxi
 * @2019/12/16 9:46
 * leetcode 205 同构字符串
 * <p>
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * <p>
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * <p>
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * <p>
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/isomorphic-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsomprphicStrings {
    public static boolean isIsomorphic1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            // 如果键不存在，值也不存在的话，则存入
            if (!map.containsKey(s.charAt(i))) {
                if (set.add(t.charAt(i))) {
                    map.put(s.charAt(i), t.charAt(i));
                } else {
                    return false;
                }
                // 下面的方法还不如上面的方法快
                /*if(!map.containsValue(t.charAt(i))){
                    map.put(s.charAt(i), t.charAt(i));
                }else{
                    return false;
                }*/
            } else {
                // 如果键存在的话，值不存在的话返回false
                if (t.charAt(i) != map.get(s.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    // 可以利用indexOf来求解字符出现的位置是否相等来解决
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s1[i]) != t.indexOf(t1[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isIsomorphic("ab", "aa"));
        System.out.println(isIsomorphic("aba", "baa"));
        System.out.println(isIsomorphic("egg", "add"));
    }
}
