package com.duanxi.byteDance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author caoduanxi
 * @Date 2020/3/9 22:20
 * 字节跳动：二叉树的右视图(递归和非递归)
 * <p>
 * 题目：
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 * <p>
 * 1            <---
 * /   \
 * 2     3         <---
 * \     \
 * 5     4       <---
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryTreeRightSideView {
    /**
     * 这是leetcode上的原题
     */
    private List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return null;
        getRightSideView(root, 0);
        return res;
    }

    private void getRightSideView(TreeNode root, int depth) {
        if (root == null) return;
        // 如果res的大小小于或者等于的话就添加否则不添加
        if (res.size() <= depth) {
            res.add(root.val);
        }
        getRightSideView(root.right, depth + 1);
        getRightSideView(root.left, depth + 1);
    }

    /**
     * 队列先进先出，每次取最边上的也就是queue里面的最后一个即可
     * @param root
     * @return
     */
    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 每次取最边上的数即可
                if (i == size - 1) {
                    list.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return list;
    }
}
