package com.duanxi.newcode;

import java.util.Stack;

/**
 * @author caoduanxi
 * @Date 2020/1/9 13:52
 * 栈的压入、弹出序列
 * 题目描述
 * <p>
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
 * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 */
public class ValidStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // 要使用栈结构
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            // 消费者模式类似，一个放，一个取
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                j++;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
