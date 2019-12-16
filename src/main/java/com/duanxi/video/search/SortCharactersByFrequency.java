package com.duanxi.video.search;


import java.util.*;

/**
 * @author caoduanxi
 * @2019/12/16 19:55
 * leetcode 451 根据字符出现频率排序
 * <p>
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * "tree"
 * <p>
 * 输出:
 * "eert"
 * <p>
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 * <p>
 * 输入:
 * "cccaaa"
 * <p>
 * 输出:
 * "cccaaa"
 * <p>
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 * <p>
 * 输入:
 * "Aabb"
 * <p>
 * 输出:
 * "bbAa"
 * <p>
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-characters-by-frequency
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortCharactersByFrequency {
    /**
     * 1.根据int数组记录出现次数，将频次相同的根据Map<Integer,String>记录在一起
     * 将出现频次的拼接在一起，最后根据map.keySet转化成数组排序，然后将所有的单独的String拼接起来即可
     *
     * 2.桶排序，根据Map<Character,Integer>记录每个字符出现的次数，获取最大的次数，建立一个桶
     * 桶按照出现的频次排序好了，只需要将对应的key存放进去即可，最后根据频次倒序遍历
     * 将char数组修改为按照频次排序即可
     */
    public static String frequencySort1(String s) {
        if (s == null || s.length() <= 0) return s;
        int[] freq = new int[256];
        Map<Integer, String> map = new HashMap<>();
        // 获取所有字符和字符出现的频率次数
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
        }
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != 0) {
                // 将其转化为字符
                char c = (char) i;
                // 次数相同的进行拼接
                // 以次数作为一个key，这样可以完美的将次数相同的汇聚在一起
                // 因为次数相同的可以放在一起
                String s1 = map.get(freq[i]);
                if (s1 != null) {
                    // 不为null的话使用了concat函数拼接
                    String newStr = s1.concat(buildStr(c, freq[i]));
                    map.put(freq[i], newStr);
                } else {
                    // 直接拼
                    map.put(freq[i], buildStr(c, freq[i]));
                }
            }
        }
        // 取出键，对键进行排序
        Integer[] keys = map.keySet().toArray(new Integer[]{});
        Arrays.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (int i = keys.length - 1; i >= 0; i--) {
            sb.append(map.get(keys[i]));
        }
        return sb.toString();
    }

    private static String buildStr(char c, int times) {
        String string = Character.toString(c);
        StringBuilder sb = new StringBuilder();

        int t = 1;
        while (t <= times) {
            sb.append(string);
            t++;
        }
        return sb.toString();
    }

    // 借鉴题解中的桶排序思想
    public static String frequencySort(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int maxTimes = -1;
        // 获取所有的字符和次数，并获取最大的次数
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            maxTimes = Math.max(maxTimes, map.get(c));
        }
        ArrayList<Character>[] buckets = new ArrayList[maxTimes + 1];
        // 这里的key注意都是唯一的
        for (Character c : map.keySet()) {
            int frequency = map.get(c);
            // 如果此时这个桶没有数，则添加桶
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(c);
        }
        // 次数已经放入相应的桶中了，此时只要从后向前遍历即可
        // 频次最大的是maxTimes
        int t = 0;
        for (int i = maxTimes; i >= 0; i--) {
            if (buckets[i] != null) {
                // 由于取出来的c都是唯一的，所以需要遍历次数
                for (Character c : buckets[i]) {
                    for (int j = 0; j < i; j++) {
                        chars[t++] = c;
                    }
                }
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));
    }

}
