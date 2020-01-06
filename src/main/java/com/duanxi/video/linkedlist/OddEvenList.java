package com.duanxi.video.linkedlist;

/**
 * @author caoduanxi
 * @Date 2020/1/6 21:28
 * leetcode 328 奇偶链表
 * <p>
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 * <p>
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 * <p>
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class OddEvenList {
    // 双指针的方式太慢了
    public ListNode oddEvenList1(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode s = new ListNode(0);
        ListNode f = new ListNode(0);
        ListNode odd = s; // 第一个记为奇数节点
        ListNode even = f;
        int i = 0; // 标记奇偶位
        while (head != null) {
            // 第一个奇数节点
            if (i % 2 == 0) {
                odd.next = head;
                head = head.next;
                odd.next.next = null;
                odd = odd.next;
            } else {
                even.next = head;
                head = head.next;
                even.next.next = null;
                even = even.next;
            }
            i++;
        }
        odd.next = f.next;
        return s.next;
    }

    //　直接利用奇偶的特性来解题
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode odd = head;
        ListNode even = head.next;
        ListNode p = even; // 偶数
        // 他们两个控制整个链表，一定要先判断odd.next，否则会报空指针，
        // 因为在赋值的时候，p.next为odd.next，保证了这个大不了为null
        while (odd.next != null && p.next != null) {
            // 利用内部的双指针，一个利用head，一个利用head.next
            // 奇偶交替执行
            odd.next = p.next;
            odd = odd.next;
            p.next = odd.next;
            p = p.next;
        }
        odd.next = even;
        return odd;
    }
}
