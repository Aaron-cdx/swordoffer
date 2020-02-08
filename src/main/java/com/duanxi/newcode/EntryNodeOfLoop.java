package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/8 11:36
 * 牛客网：链表中环的入口节点
 * <p>
 * 题目：
 * 给一个链表，若其中包含环，
 * 请找出该链表的环的入口结点，否则，输出null。
 */
public class EntryNodeOfLoop {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 应该是快慢指针的原理:
     * 快慢指针的话，应该是刚开始快指针一次走两步
     * 慢指针一次走一步，第一次相遇的时候，快指针回到头部
     * 然后与满指针一起走，再次相遇就是环的切入点。
     * <p>
     * 原理如下:
     * 因为快指针第一次相遇慢指针的时候，由于速度是两倍，此时快指针领先一般的路程
     * 因为快指针在环里面才能遇到慢指针，此时快指针回到头部的话，与慢指针一起走
     * 再次相遇一定是环的切入点。
     * <p>
     * 判断链表是否成环，只需要判断是否能相遇即可，相遇证明有环
     * 如果没环，只要fast=null，两者都是没有机会相遇的。
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) return null;
        ListNode slow = pHead;
        ListNode fast = pHead;
        boolean hasLoop = false;
        // 如果存在环的话，输出入口节点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                hasLoop = true;
                break;
            }
        }
        // 只有进环之后fast有可能追上slow
        if (!hasLoop) return null;
        // 追上之后快指针应该回到头部一步一步
        fast = pHead;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}

