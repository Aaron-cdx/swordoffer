package com.duanxi.video.linkedlist;

/**
 * @author caoduanxi
 * @Date 2020/1/6 20:40
 * leetcode 86 分隔链表
 * <p>
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * <p>
 * 你应当保留两个分区中每个节点的初始相对位置。
 * <p>
 * 示例:
 * <p>
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PartitionList {
    // 双指针之间的关系注意他们之间的引用关系
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        // 使用双指针
        ListNode s = new ListNode(0);
        ListNode f = new ListNode(0);
        ListNode s1 = s;
        ListNode f1 = f;
        while(head != null){
            if(head.val < x){
                // s1的下一个节点指向head
                s1.next = head;
                // head去到下一个地方
                head = head.next;
                // 将s1的下一个下一个置为null，因为现在head的连接还在，这个就是将head.next的指向改为null
                s1.next.next = null;
                // s1往下一个走
                s1 = s1.next;
            }else{
                f1.next = head;
                head = head.next;
                f1.next.next = null;
                f1 = f1.next;
            }
        }
       s1.next = f.next;
       return s.next;
    }
}
