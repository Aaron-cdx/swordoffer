package com.duanxi.newcode;


/**
 * @author caoduanxi
 * @Date 2020/2/4 19:29
 * 牛客网：找两个链表的公共节点
 * <p>
 * 题目：
 * 给出连个链表，找出它们的公共节点。
 */
public class FindFirstCommonNode {
    // 第一种方法是记录长度，然后走到相同长度处然后比较
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        // 获取两个链表的长度
        int len1 = getListNodeLen(pHead1);
        int len2 = getListNodeLen(pHead2);
        if (len1 > len2) {
            pHead1 = walkToLen(pHead1, len1 - len2);
        } else {
            pHead2 = walkToLen(pHead2, len2 - len1);
        }
        // 随意挑，如果有一方为null的话直接返回null即可
        while (pHead1 != null) {
            if (pHead1 == pHead2) return pHead1;
            pHead1 = pHead1.next;
            pHead2 = pHead2.next;
        }
        return null;
    }

    public int getListNodeLen(ListNode head) {
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        return len;
    }

    public ListNode walkToLen(ListNode head, int len) {
        // 需要向前走len个长度
        while (len != 0) {
            head = head.next;
            len--;
        }
        return head;
    }

    // 第二种方法
    public ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != p2) {
            // 如果一个走到了尽头，那么对方一定走到了相同的节点
            p1 = p1 == null ? p2 : p1.next;
            p2 = p2 == null ? p1 : p2.next;
        }
        return p1;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        this.val = x;
    }
}
