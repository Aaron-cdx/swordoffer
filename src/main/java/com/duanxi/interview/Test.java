package com.duanxi.interview;

import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author caoduanxi
 * @Date 2020/1/13 11:27
 */
public class Test {
    public static boolean getTrue(String string){
        if(string.length() == 0 || string == null) return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < string.length(); i++) {
            if("(".equals(string.charAt(i)) || "{".equals(string.charAt(i))
            || "[".equals(string.charAt(i))){
                stack.push(string.charAt(i));
            }else{
                if(!stack.isEmpty() && stack.peek().equals(getCharacter(string.charAt(i)))){
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    public static char getCharacter(char c){
        if(")".equals(c)){
            return '(';
        }else if("]".equals(c)){
            return '[';
        }else{
            return '{';
        }
    }


    public static void main(String[] args) {
        System.out.println(getTrue("([)]"));
        System.out.println(getTrue("{[]}"));
        ConcurrentHashMap map = null;
    }
}
