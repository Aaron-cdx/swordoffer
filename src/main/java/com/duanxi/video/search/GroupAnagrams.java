package com.duanxi.video.search;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2019/12/24 22:15
 * leetcode 49 字母异位词分组
 * <p>
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GroupAnagrams {
    public static List<List<String>> groupAnagrams1(String[] strs) {
        // 排序，然后对应输出即可
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            // 排序主要是为了比较，存还是存strs[i]
            Arrays.sort(chars);
            String string = String.copyValueOf(chars);
            // 第一次出现，放入创建一个list用于存储
            if (!map.containsKey(string)) {
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(string, list);
            } else {
                List<String> list = map.get(string);
                list.add(strs[i]);
                map.put(string, list);
            }
        }
        // 遍历所有的key值
        Set<String> strings = map.keySet();
        for (String string : strings) {
            res.add(map.get(string));
        }
        return res;
    }

    // 第二种解法人为的改变key的构造，使得strs[]中相同的异位词key排列标准一致
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        int[] word = new int[26];
        for (String str : strs) {
            Arrays.fill(word, 0);
            // 一个一个处理
            for (int i = 0; i < str.length(); i++) {
                word[str.charAt(i) - 'a']++;
            }
            // 处理完一个之后，变换key
            String key = "";
            for (int i = 0; i < word.length; i++) {
                // 需要每个人都加，否则值就一样了
                key = key + word[i] + "#";
            }
            if (!map.containsKey(key)) {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            } else {
                map.get(key).add(str);
            }
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        List<List<String>> lists = groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        System.out.println(lists);
    }
}
