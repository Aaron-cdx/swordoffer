package com.duanxi.interview.Aiqiyi.three;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author caoduanxi
 * @Date 2020/8/23 15:30
 * 验证是否是有效的括号
 * 注意空字符是有效的，遇到空直接跳过
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        String str = scanner.nextLine();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == ' ') continue;
            if(stack.isEmpty()){
                stack.push(str.charAt(i));
            }else{
                if(stack.peek() == main.getChar(str.charAt(i))){
                    stack.pop();
                }else{
                    stack.push(str.charAt(i));
                }
            }
        }
        String s = stack.isEmpty() ? "True" : "False";
        System.out.println(s);
    }

    public char getChar(char c){
        if(c == ']'){
            return '[';
        }else if(c == ')'){
            return '(';
        }else{
            return '{';
        }
    }
}
