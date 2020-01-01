package com.duanxi.video.linkedlist;

/**
 * @author caoduanxi
 * @Date 2020/1/1 20:41
 * leetcode 206 反转链表
 * <p>
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseLinkedList {
    // 链表反转
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    // 通过数组构建链表
    public static ListNode constructListNode(int[] arr) {
        ListNode head = new ListNode(1);
        ListNode cur = head;
        for (int i : arr) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        return head.next;
    }

    // 打印链表
    public static void printListNode(ListNode head) {
        if (head == null) return;
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        ListNode listNode = constructListNode(arr);
        printListNode(listNode);
        System.out.println();
        ListNode listNode1 = reverseList(listNode);
        printListNode(listNode1);
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        this.val = x;
    }
}
