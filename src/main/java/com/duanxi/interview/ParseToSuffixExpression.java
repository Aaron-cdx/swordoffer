package com.duanxi.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author caoduanxi
 * @Date 2020/1/8 14:58
 */
public class ParseToSuffixExpression {
    private static List<String> parseToMidSuffix(String expression) {
        List<String> midSuffix = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            midSuffix.add(String.valueOf(expression.charAt(i)));
        }
        return midSuffix;
    }

    // 中缀表达式转后缀表达式
    private static List<String> parseMidSuffixToSuffix(List<String> midSuffixExpress) {
        Stack<String> stack = new Stack<>();
        List<String> res = new ArrayList<>();
        for (String item : midSuffixExpress) {
            // 先判断是否是操作符
            if (isOperator(item)) {
                // 是操作符的话，判断栈中是否有数据，为空直接压入
                // 如果是为左括号的话直接压入，如果此优先级大于栈顶操作符优先级压入
                // 第三种情况就是要确保如果遇到先*后加的情况，要保证*号弹出
                // 1+2*3+4  如果到了第二个+号的时候，此时需要先将*号弹出来，否则就会发生错误
                if (stack.isEmpty() || "(".equals(stack.peek()) || priority(item) > priority(stack.peek())) {
                    stack.push(item);
                } else {
                    // 如果不为空且与优先级也不高的话，先把优先级高的弹出放在队列中
                    // 这里主要是要弹出部分的符号
                    while (!stack.isEmpty() && !"(".equals(stack.peek())) {
                        if (priority(item) <= priority(stack.peek())) {
                            res.add(stack.pop());
                        }
                    }
                    stack.push(item);
                }
            } else if (isNumber(item)) {
                // 数字的话直接入列表
                res.add(item);
            } else if ("(".equals(item)) {
                // 左括号的话,入栈
                stack.push(item);
            } else if (")".equals(item)) {
                // 如果是右括号的话，需要将之前的出栈，知道遇到左括号
                // 如果是遇到右括号，一定要弹出所有的直到遇到左边的括号，且左右括号不能进入列表list中
                while (!stack.isEmpty()) {
                    if ("(".equals(stack.peek())) {
                        stack.pop();// 将这个左括号弹出
                        break;
                    }
                    // 否则的话都入栈
                    res.add(stack.pop());
                }
            } else {
                try {
                    throw new Exception("输入的表达式有非法字符！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        // 如果到最后栈还不为空，则全都出栈
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }

    /**
     * 判断是否是操作符+ - * /
     *
     * @param str
     * @return
     */
    private static boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }

    /**
     * 判断是否是数字，用正则来匹配
     *
     * @param str
     * @return
     */
    private static boolean isNumber(String str) {
        return str.matches("\\d+");
    }

    /**
     * 判断运算符之间的优先级问题
     *
     * @param str
     * @return
     */
    private static int priority(String str) {
        if (str.equals("*") || str.equals("/")) {
            return 1;
        } else if (str.equals("+") || str.equals("-")) {
            return 0;
        }
        return -1;
    }

    // 计算最后的结果
    public static int calculate(List<String> suffix) {
        // 存入遇到运算符就弹出即可
        Stack<String> stack = new Stack<>();
        for (String s : suffix) {
            if (isNumber(s)) {
                stack.push(s);
            } else {
                int a2 = Integer.parseInt(stack.pop());
                int a1 = Integer.parseInt(stack.pop());
                if ("+".equals(s)) {
                    stack.push(String.valueOf(a1 + a2));
                } else if ("-".equals(s)) {
                    stack.push(String.valueOf(a1 - a2));
                } else if ("*".equals(s)) {
                    stack.push(String.valueOf(a1 * a2));
                } else if ("/".equals(s)) {
                    stack.push(String.valueOf(a1 / a2));
                }
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static void main(String[] args) {
        // 这是获取的中缀表达式
        List<String> midSuffix = parseToMidSuffix("1+((2+3)*4)-5");
        System.out.println(midSuffix);
        // 转后缀表达式
        List<String> suffixList = parseMidSuffixToSuffix(midSuffix);
        System.out.println(suffixList);
        // 计算结果
        System.out.println(calculate(suffixList));
    }
}
