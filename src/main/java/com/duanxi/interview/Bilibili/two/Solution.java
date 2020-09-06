package com.duanxi.interview.Bilibili.two;

import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean b = solution.IsValidExp("{[()]}");
        System.out.println(b);
    }
    /**
     * 
     * @param s string字符串 
     * @return bool布尔型
     * 使用两个栈来做处理
     */
    public boolean IsValidExp (String s) {
        // write code here
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char aChar : chars) {
            if(stack.isEmpty()){
                stack.push(aChar);
            }else {
                if (stack.peek() == getChar(aChar)) {
                    stack.pop();
                } else {
                    stack.add(aChar);
                }
            }
        }
        return stack.isEmpty();
    }

    public char getChar(char c){
        if(c == '{'){
            return '}';
        }else if(c == '}'){
            return '{';
        }else if(c == '['){
            return ']';
        }else if(c ==']'){
            return '[';
        }else if(c == '('){
            return ')';
        }else{
            return '(';
        }
    }
}