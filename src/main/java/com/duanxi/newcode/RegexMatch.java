package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/7 15:03
 * 牛客网：正则表达式匹配
 * <p>
 * 题目：
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。
 * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */
public class RegexMatch {
    /**
     * str是输入的字符串
     * pattern是需要判断是否与输入字符串匹配的字符串
     */
    public boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) return false;
        /*int strIndex = str.length - 1;
        int patternIndex = pattern.length - 1;*/
        int strIndex = 0;
        int patternIndex = 0;
        return matchRegular(str, pattern, strIndex, patternIndex);
    }

    private boolean matchRegular(char[] str, char[] pattern, int strIndex, int patternIndex) {
        // 一起到最后，返回true
        if (strIndex == str.length && patternIndex == pattern.length) return true;

        // 模式先到尾部，直接返回false
        if (strIndex != str.length && patternIndex == pattern.length) return false;

        // 首先保证不越界,发现自己后面有*出现
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            // 如果含有*号的话表示出现0次或者无数次
            // 如果能够匹配继续走或者为'.'也继续走
            if ((strIndex != str.length && pattern[patternIndex] == str[strIndex])
                    || (pattern[patternIndex] == '.' && strIndex != str.length)) {
                // 向后移动2个单位，意味着匹配0个字符
                return matchRegular(str, pattern, strIndex, patternIndex + 2)
                        // 匹配一个字符
                        || matchRegular(str, pattern, strIndex + 1, patternIndex + 2)
                        // 表示'*'匹配了一个，在匹配下一个，因为'*'可以匹配多位操作
                        || matchRegular(str, pattern, strIndex + 1, patternIndex);
            } else {
                // 表示一个也没匹配上
                return matchRegular(str, pattern, strIndex, patternIndex + 2);
            }
        }
        // 这个就只能一对一匹配了否则返回false
        if ((strIndex != str.length && pattern[patternIndex] == str[strIndex])
                || (pattern[patternIndex] == '.' && strIndex != str.length)) {
            return matchRegular(str, pattern, strIndex + 1, patternIndex + 1);
        }
        return false;
    }




    /*private boolean matchRegular(char[] str, char[] pattern, int strIndex, int patternIndex) {
        // 从后向前匹配,只要下标索引不同直接返回false
        if (strIndex == -1 && patternIndex == -1) return true;
        if (strIndex != 0 && patternIndex == 0) return false;
        // 终止条件
        if (pattern[patternIndex] == '*') {
            // 此时需要向前遍历直到出现不同的值停止
            --patternIndex; // 当前为*号的时候，直接前进移位
            while (patternIndex - 1 >= 0) {
                if (pattern[patternIndex] != pattern[patternIndex - 1]) {
                    break;
                }
                // 如果相等的话，继续前进
                patternIndex--;
            }
            if(pattern[patternIndex] != '.'){
                patternIndex--;
            }
            return matchRegular(str, pattern, strIndex, patternIndex);
        } else if (pattern[patternIndex] == str[strIndex] || pattern[patternIndex] == '.') {
            return matchRegular(str, pattern, --strIndex, --patternIndex);
        }
        return false;
    }*/

    public static void main(String[] args) {
        RegexMatch test = new RegexMatch();
        boolean res = test.match("a".toCharArray(), ".*".toCharArray());
        boolean res3 = test.match("".toCharArray(), ".*".toCharArray());
        // *能匹配多位
        boolean res1 = test.match("aaa".toCharArray(), "a*".toCharArray());
        boolean res2 = test.match("aaa".toCharArray(), "ab*a".toCharArray());
        System.out.println(res);
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
}
