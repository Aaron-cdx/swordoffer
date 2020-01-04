package com.duanxi.video.linkedlist;

/**
 * @author caoduanxi
 * @Date 2020/1/4 19:27\
 * leetcode 83 删除排序链表中的重复元素
 * <p>
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 * <p>
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveDuplicateFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        while (cur.next != null) {
            // 在这里是要将cur.next移除掉
            if (cur.val == cur.next.val) {
                // 记录它，如果遇到相等的话，就删除它
                ListNode temp = cur.next;
                cur.next = temp.next;
                // 后面只需要将这个指针指向null即可
                temp.next = null;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}

