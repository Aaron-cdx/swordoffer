package com.duanxi.books.charpeterTwo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author caoduanxi
 * @2019/12/12 22:05
 * 剑指offer 面试题9-II 用两个队列实现一个栈
 * <p>
 * leetcode 225
 * 使用队列实现栈的下列操作：
 * <p>
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 * 注意:
 * <p>
 * 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
 * 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-stack-using-queues
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Nineth_ImplementsStackUseTwoQueue {
    /**
     * Initialize your data structure here.
     */
    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    public Nineth_ImplementsStackUseTwoQueue() {

    }
    // 5 4 3 2 1
    // 1 2 3 4 5
    /**
     * 解题思路：
     *
     * 每次a存入数据的时候，由于队列是先进先出操作的，此时需要将进来的数据存到2中去，如果直接存肯定不行
     * 因为队列是先进先出的类型的，此时需要直接转换即可，将a b兑换，当下次进来数据的时候
     * 直接存，然后将b中的数据插入，形成数据的后插入，然后再将数据a与b对调
     *
     * top的时候，此时只需要直接从b中弹出即可，因为b就充当了栈！
     */
    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        // 保证2为空 从尾部放入的
        // 这里是一定确保第二个队列永远存放的都是逆序的
        // 首先队列先添加
        queue1.offer(x);
        // 判断第二个队列是否为空，不为空的话，此时2就是逆序的
        // 只要把3放到他们的前面，就变成了3 2 1 弹出顺序就为 3 2 1符合栈
        while (!queue2.isEmpty()) {
            queue1.offer(queue2.poll());
        }
        // 第一次为空的话，两者交换，保证2在1之后插入
        // 保证3在2和1之后插入
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return queue2.poll();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return queue2.peek();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
