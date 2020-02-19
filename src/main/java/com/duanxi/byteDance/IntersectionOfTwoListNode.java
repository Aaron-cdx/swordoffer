package com.duanxi.byteDance;

import java.util.LinkedList;

/**
 * @author caoduanxi
 * @Date 2020/2/19 10:02
 * 字节跳动：获取两个链表的交点
 * <p>
 * 题目：
 * 找出两个链表的第一个公共节点，复杂度要求O(n)，空间复杂度O(1)
 */
public class IntersectionOfTwoListNode {
    /**
     * 根据题目要求的复杂度，不能用任何辅助空间
     * 1->2->3->4->5
     * 3->4->5
     * 得到第一个链表长度为5，第二个为3
     * 此时只需要第一个走2步，相等即为交点
     */
    public ListNode getIntersection(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        int len1 = 0;
        int len2 = 0;
        // 获取两个链表的长度 ==> 可以单独提出来作为一个方法
        ListNode cur = pHead1;
        while (cur != null) {
            len1++;
            cur = cur.next;
        }
        cur = pHead2;
        while (cur != null) {
            len2++;
            cur = cur.next;
        }
        // 长度长的需要先走===> 也可单独提出作为一个方法
        if (len1 > len2) {
            int len = len1 - len2;
            while (len != 0) {
                len--;
                pHead1 = pHead1.next;
            }
        }
        if (len1 < len2) {
            int len = len2 - len1;
            while (len != 0) {
                len--;
                pHead2 = pHead2.next;
            }
        }
        // 一起走，相遇的话就是公共交点
        while (pHead1 != pHead2) {
            pHead1 = pHead1.next;
            pHead2 = pHead2.next;
        }
        return pHead1;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
