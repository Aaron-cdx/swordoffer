package com.duanxi.byteDance;

/**
 * @author caoduanxi
 * @Date 2020/2/19 14:37
 * 字节面试：奇偶升序链表
 * <p>
 * 题目：
 * 一个链表奇数位升序，偶数位降序，将链表变为升序的链表。
 */
public class ParityAscendingLinkedList {
    public ListNode getAscending(ListNode head) {
        if (head == null || head.next == null) return head;
        // 奇偶分开
        ListNode odd = null;
        ListNode even = null;
        ListNode curEven = null;
        ListNode curOdd = null;
        int count = 1;
        while (head != null) {
            // 奇数位
            if (count % 2 != 0) {
                if (curOdd != null) {
                    curOdd.next = head;
                    curOdd = curOdd.next;
                } else {
                    // 只在第一次确定头部节点
                    curOdd = head;
                    odd = curOdd;
                }
            } else {
                // 偶数位
                if (curEven != null) {
                    curEven.next = head;
                    curEven = curEven.next;
                } else {
                    curEven = head;
                    even = curEven;
                }
            }
            head = head.next;
            count++;
        }
        curEven.next = null;
        curOdd.next = null;

        // curOdd是顺序的，curEven是逆序的
        even = reverseListNode(even);
        ListNode dummyNode = new ListNode(0);
        ListNode resNode = dummyNode;
        // 比大小放置元素
        while (even != null && odd != null) {
            if (even.val >= odd.val) {
                resNode.next = odd;
                odd = odd.next;
            } else {
                resNode.next = even;
                even = even.next;
            }
            resNode = resNode.next;
        }
        // 如果还有剩下多的元素，直接放在后面即可
        if (even != null) {
            resNode.next = even;
        }
        if (odd != null) {
            resNode.next = odd;
        }
        return dummyNode.next;
    }

    private ListNode reverseListNode(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode preNode = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = preNode;
            preNode = head;
            head = temp;
        }
        return preNode;
    }

    public static void main(String[] args) {
        ParityAscendingLinkedList test = new ParityAscendingLinkedList();
        ListNode head = new ListNode(1);
        ListNode cur = head;
        int[] arr = new int[]{8, 3, 6, 5, 4, 7, 2, 9};
        // 构造链表
        for (int i : arr) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }

        ListNode listNode = test.getAscending(head);
        while (listNode != null) {
            System.out.print(listNode.val + "->");
            listNode = listNode.next;
        }
        System.out.print("null");
    }
}
