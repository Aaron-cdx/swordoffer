package com.duanxi.video.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author caoduanxi
 * @Date 2020/1/9 8:59
 * leetcode 226 翻转二叉树
 */
public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left; // 只交换左右节点
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    private TreeNode execute(TreeNode root) {
        if (root == null) return null;
        TreeNode node = root;

        node.right = execute(root.left);
        node.left = execute(root.right);

        return node;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            List<Integer> temp = new ArrayList<>();
            while (count > 0) { // 直接将所有的都吐出来
                TreeNode poll = queue.poll();
                temp.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);

                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
                count--;
            }
            list.add(temp);
        }
        return list;
    }
}
