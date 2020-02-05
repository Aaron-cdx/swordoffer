package com.duanxi.newcode;

import javax.swing.tree.TreeNode;

/**
 * @author caoduanxi
 * @Date 2020/2/5 10:41
 * 牛客网：二叉树的深度
 * <p>
 * 题目：
 * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）
 * 形成树的一条路径，最长路径的长度为树的深度。
 */
public class TreeDepth {
    /**
     * 思路：直接利用树的左子树和右子树来计算深度
     * 利用递归来执行，如果为null的话直接返回0
     * 如果不为null的话，通过左右子树依次+1向下递归判断
     */
    public int TreeDepth(TreeNode root) {
        if (root == null) return 0;
        return helper(root);
    }

    public int helper(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = 1;
        int rightDepth = 1;
        if (root.left != null) {
            leftDepth += helper(root.left);
        }
        if (root.right != null) {
            rightDepth += helper(root.right);
        }
        return Math.max(leftDepth, rightDepth);
    }

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }
}


