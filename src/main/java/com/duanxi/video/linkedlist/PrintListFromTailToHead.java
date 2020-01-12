package com.duanxi.video.linkedlist;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author caoduanxi
 * @Date 2020/1/8 16:01
 */
public class PrintListFromTailToHead {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        ListNode cur = listNode;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        while (stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }

    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(0);
        ListNode cur = dummyNode;
        ListNode l = list1;
        ListNode r = list2;
        while (l != null && r != null) {
            if (l.val > r.val) {
                cur.next = new ListNode(r.val);
                r = r.next;
            } else {
                cur.next = new ListNode(l.val);
                l = l.next;
            }
            cur = cur.next;
        }
        while (l != null) {
            cur.next = new ListNode(l.val);
            cur = cur.next;
            l = l.next;
        }
        while (r != null) {
            cur.next = new ListNode(r.val);
            cur = cur.next;
            r = r.next;
        }
        return dummyNode.next;
    }
}
