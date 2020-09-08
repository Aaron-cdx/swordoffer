package com.duanxi.interview.Xiaomi.one;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: caoduanxi
 * @Date: 2020/9/8
 * 验证当前密码是否符合规则
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        String str = scanner.nextLine();
        String[] s = str.split(" ");
        for (String s1 : s) {
            System.out.println(main.testStr(s1));
        }
    }

    public int testStr(String str) {
        // 满足要求返回0
        // 长度不满足返回1
        // 类型不满足返回2
        int len = str.length();
        if (!(len >= 8 && len <= 120)) return 1;
        // 校验是否满足规则,使用正则表达式来操作
        // 规则就是需要包含数字、字符、大写字母、小写字母
//        String pattern = "^[\\w\\d\\$\\^!.,\\*[a-z][A-Z]]";
//        String pattern = "[\\w.][\\d.]\\$\\^[\\.{1,}][a-z][A-Z]]";
        if (str.matches("^(?![A-Za-z0-9]+$)(?![a-z0-9\\\\W]+$)(?![A-Za-z\\\\W]+$)(?![A-Z0-9\\\\W]+$)[a-zA-Z0-9\\\\W]{8,120}$")) {
            return 0;
        }
        return 2;
    }
}
