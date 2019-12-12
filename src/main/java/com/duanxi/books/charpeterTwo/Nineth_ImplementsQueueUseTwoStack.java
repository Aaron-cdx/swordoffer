package com.duanxi.books.charpeterTwo;

import java.util.Stack;

/**
 * @author caoduanxi
 * @2019/12/12 21:42
 * 剑指offer 面试题9 用两个栈实现队列
 * <p>
 * 题目：用两个栈实现一个队列。实现两个函数插入尾节点和删除头结点
 * 分别完成在队列尾部插入节点和在队列头部删除节点的功能
 * <p>
 * leetcode 232题 用栈实现队列
 * <p>
 * 使用栈实现队列的下列操作：
 * <p>
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Nineth_ImplementsQueueUseTwoStack {

    /**
     * Initialize your data structure here.
     */
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    // 一定要保持一个栈是空的
    public Nineth_ImplementsQueueUseTwoStack() {

    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        // 如果第二个栈是空的话，可以压栈
        if(stack2.isEmpty()){
            stack1.push(x);
        }else{
            while(!stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
            // 然后再放
            stack1.push(x);
        }
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        // 弹出的话
        if(stack1.isEmpty()){
            return  stack2.pop();
        }else{
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
    }

    /**
     * Get the front element.
     */
    public int peek() {
        // 弹出的话
        if(stack1.isEmpty()){
            return stack2.peek();
        }else{
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            return stack2.peek();
        }
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return stack2.isEmpty() && stack1.isEmpty();
    }
}
