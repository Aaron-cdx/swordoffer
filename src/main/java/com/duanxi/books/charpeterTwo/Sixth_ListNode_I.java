package com.duanxi.books.charpeterTwo;

/**
 * @author caoduanxi
 * @2019/12/8 20:58
 * 链表的结构 ==> 复习链表
 */
public class Sixth_ListNode_I {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
        }
    }

    // 实现链表的添加元素
    private static ListNode addNode(ListNode head, int num) {
        if (head == null) return null;

        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = new ListNode(num);
        cur.next.next = null;

        return head;
    }
    // 链表的删除
    private static ListNode removeNode(ListNode head, int num){
        if(head == null) return null;
        // 人为构造一个节点用于删除头结点，方便
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode cur = dummyNode;
        while(cur.next != null){
            if(cur.next.val == num){
                ListNode temp = cur.next.next;
                cur.next.next = null;
                cur.next = temp;
                break;
            }
            cur = cur.next;
        }
        // 所有的都是通过指针指向来执行的
        return dummyNode.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode a = addNode(head, 2);
        ListNode listNode = addNode(a, 3);
//        while (listNode != null) {
//            System.out.println(listNode.val);
//            listNode = listNode.next;
//        }
        ListNode listNode1 = removeNode(listNode, 2);
        while (listNode1 != null) {
            System.out.println(listNode1.val);
            listNode1 = listNode1.next;
        }
    }

}
