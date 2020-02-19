package com.duanxi.byteDance;

/**
 * @author caoduanxi
 * @Date 2020/2/19 10:21
 * 字节跳动：链表对折
 * <p>
 * 题目：
 * 将链表对折后输出
 */
public class FoldTheListNode {
    /**
     * 思路：
     * 使用快慢指针找到链表的中点，然后分割链表，找出前后的链表。
     * 分别反转，然后将链表连接起来即可
     */
    public ListNode foldListNode(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head;
        ListNode slow = head;
        // 使用快慢指针找到中点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // fast到达了尾部，slow到达了中点
        // 这里要切断！然后分别反转
        // 让fast作为后半段
        fast = slow.next;
        // head作为前半段，因为slow.next切断了链表
        slow.next = null;
        ListNode pre = reverseListNode(fast);
        // 拼接起来
        // 注意这里要遍历pre所有的节点到最后才能拼接
        ListNode cur = pre;
        while (cur.next != null) {
            cur = cur.next;
        }
        // 拼接
        cur.next = reverseListNode(head);
        return pre;
    }

    /**
     * 这一部分是链表反转
     */
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
        FoldTheListNode test = new FoldTheListNode();
        ListNode head = new ListNode(1);
        ListNode cur = head;
        // 构造链表
        for (int i = 2; i <= 10; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }

        ListNode listNode = test.foldListNode(head);
        while (listNode != null) {
            System.out.print(listNode.val + "->");
            listNode = listNode.next;
        }
        System.out.print("null");
    }
}
