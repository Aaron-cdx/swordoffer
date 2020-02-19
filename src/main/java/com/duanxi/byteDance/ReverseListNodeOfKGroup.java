package com.duanxi.byteDance;

/**
 * @author caoduanxi
 * @Date 2020/2/19 11:01
 * 字节跳动：K个一组翻转链表
 * <p>
 * 题目：
 * 给定一个链表，每K个节点一组进行翻转，请你返回翻转后的链表。
 */
public class ReverseListNodeOfKGroup {
    /**
     * 思路：
     * 利用一个dummyNode节点，每次寻址到k个处，然后进行翻转，每次转移头节点
     * 利用头节点不断向后移动来获取翻转的连接，最后获取答案
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) return head;
        // 借助一个dummyNode节点
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        ListNode end = dummyNode;
        int i;
        while (end.next != null) {
            i = 0;
            // 主要目的是找到要分界的点
            while (i < k && end != null) {
                end = end.next;
                i++;
            }
            if (end == null) break;
            ListNode start = pre.next;
            // 保留end后面的节点作为临时节点
            ListNode temp = end.next;
            // 切断
            end.next = null;
            // 翻转前面部分,直接将指针指导翻转后的头节点即可
            pre.next = reverseListNode(start);
            // 斜街第一次翻转的与后面未翻转的
            start.next = temp;
            pre = start; // start的话是未翻转之前的首节点，翻转之后的尾节点
            end = pre;
        }
        return dummyNode.next;
    }

    public ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) return head;
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        ListNode end = dummyNode;
        int index;
        // 至少是一个起步，所以需要保证end.next不能为null
        while (end.next != null) {
            index = 0;
            while (index < k && end != null) {
                end = end.next;
                index++;
            }
            // 如果为null的话，直接break，跳出循环不翻转
            if (end == null) break;
            // 获取翻转头节点，切断要翻转部分，进行翻转
            ListNode start = pre.next;
            ListNode temp = end.next;
            // 切断
            end.next = null;

            pre.next = reverseListNode(start);
            // 衔接start与temp
            start.next = temp;
            pre = start;
            end = pre;
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
        ReverseListNodeOfKGroup test = new ReverseListNodeOfKGroup();
        ListNode head = new ListNode(1);
        ListNode cur = head;
        // 构造链表
        for (int i = 2; i <= 5; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }

        ListNode listNode = test.reverseKGroup(head, 2);
        while (listNode != null) {
            System.out.print(listNode.val + "->");
            listNode = listNode.next;
        }
        System.out.print("null");
    }
}
