package com.duanxi.newcode;

import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/2/8 12:26
 * 牛客网：删除链表中重复的节点
 * <p>
 * 题目:
 * 在一个排序的链表中，存在重复的结点，
 * 请删除该链表中重复的结点，重复的结点不保留，
 * 返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class DeleteDuplication {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode deleteDuplication(ListNode pHead) {
        // 没有元素或者只有一个元素，返回当前pHead即可
        if (pHead == null || pHead.next == null) return pHead;
        // 因为可能删除的是第一个元素
        ListNode dummyNode = new ListNode(1);
        dummyNode.next = pHead;
        ListNode pre = dummyNode;
        ListNode cur = dummyNode.next;
        while(cur != null){
            if(cur.next != null && cur.val == cur.next.val){
                // 一旦遇到重复的元素，找到非重复的第一个元素
                while(cur.next != null && cur.val == cur.next.val){
                    cur = cur.next;
                }
                // 直接把指针迁移过来 cur向后行走
                pre.next = cur.next;
                cur = cur.next;
            }else{
                // 没有重复的话，大家各自向后走
                pre = pre.next;
                cur = cur.next;
            }
        }
        return dummyNode.next;
    }
}
