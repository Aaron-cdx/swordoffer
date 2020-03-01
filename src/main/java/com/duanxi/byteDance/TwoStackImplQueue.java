package com.duanxi.byteDance;

import java.util.Stack;

/**
 * @author caoduanxi
 * @Date 2020/3/1 8:05
 * 字节跳动：使用两个栈实现队列
 * <p>
 * 题目:
 * 使用两个栈实现队列,完成push和pop操作
 */
public class TwoStackImplQueue {
    /**
     * 思路：
     * 首先建立两个栈s1 s2
     * 其次保持s2在操作的时候永远只有一个数，但是操作完之后吐出顺序却是队列
     * s1的作用就是暂时存储数据。
     * 例如插入1
     * 此时s2为空，s2: 1, s1: null
     * 插入2时：s2:2  s1:1  操作结束：s2: 1 2  s1: null
     * 插入3时：s2:3  s1:2 1 操作结束： s2: 1 2 3  s1: null
     */
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    public void push(int num) {
        // 如果2不是空的
        if (!stack2.isEmpty()) {
            // 此时将2中所有的放到1中,2保持始终进来的数就是在1中最先出去的
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            // 给2新增一个数
            stack2.push(num);
            // 将1中的数放入，保持始终进来的都是最后出去的，实现队列
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        } else {
            // 为空的话就先插入即可
            stack2.push(num);
        }
    }

    public int pop() {
        return stack2.pop();
    }

    public static void main(String[] args) {
        TwoStackImplQueue test = new TwoStackImplQueue();
        test.push(1);
        System.out.println(test.pop());
        test.push(2);
        test.push(3);
        System.out.println(test.pop());
        System.out.println(test.pop());

    }
}

