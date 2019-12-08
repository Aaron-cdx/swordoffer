package com.duanxi.books.charpeterTwo;

import java.util.Stack;

/**
 * @author caoduanxi
 * @2019/12/8 21:21
 * 面试题6 从头到尾打印链表
 * <p>
 * 题目：输入一个链表的头结点，从尾到头反过来打印出每个节点的值
 * <p>
 * 考察的对单向链表的理解和编程能力
 * 考察对循环、递归和栈3个互相关联的概念的理解
 * <p>
 * 这里注意，如果面试需要和面试官沟通是否能够通过修改链表的结构来实现从头到尾打印
 */
public class Sixth_PrintListNodeReversingly {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
        }
    }

    // 使用栈结构实现
    private static void PrintListNodeReversing1(ListNode head) {
        // 使用栈结构来实现
        if (head == null) return;
        Stack<Integer> stack = new Stack<>();

        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    // 使用递归实现到了最后一个开始打印
    private static void PrintListNodeReversing2(ListNode head) {
        // 如果为null，表示到了最后一个节点的位置
        if (head == null) return;
        // 如果没到的继续递归
        PrintListNodeReversing2(head.next);
        // 打印出来即可
        System.out.println(head.val);
    }

    // 实现链表翻转然后打印
    private static void PrintListNodeReversing(ListNode head) {
        if (head == null) return;
        ListNode pre = null;
        ListNode cur = head;
        // null <- 1 -> 2 -> 3 -> 4
        // 首选需要将1的下面的保存
        while (cur != null) {
            // 保存1节点后面的
            ListNode temp = cur.next;
            // 将1节点的指针指向pre
            cur.next = pre;
            // 移动pre到1的位置
            pre = cur;
            // 当前的1移动到2位置
            cur = temp;
        }
        while (pre != null) {
            System.out.println(pre.val);
            pre = pre.next;
        }
    }

    public static void main(String[] args) {
        // 为null的测试数据
//        ListNode head = null;
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        PrintListNodeReversing(head);
    }

}
