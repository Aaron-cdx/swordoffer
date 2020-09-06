package com.duanxi.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author caoduanxi
 * @Date 2020/8/10 22:29
 */
public class Solution {
    Long bstVal = Long.MIN_VALUE;

    // 通过中序遍历来解决这个问题
    // 且方法为递归
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (isValidBST(root.left)) {
            if (bstVal < root.val) {
                bstVal = (long) root.val;
                return isValidBST(root.right);
            }
        }
        return false;
    }

    /**
     * 不采用中序遍历的方式来判断
     * 当前的数有一个上限和一个下限
     * 只需要卡住边界值即可
     */
    public boolean isValidBST_II(TreeNode root) {
        if (root == null) return true;

        return isValidHelper(root,Long.MAX_VALUE,Long.MIN_VALUE);
    }

    public boolean isValidHelper(TreeNode root, long max, long min) {
        if (root == null) return true;
        // 左右边界分别卡住
        if(root.val <= min || root.val >= max){
            return false;
        }
        return isValidHelper(root.left,root.val,min)
                && isValidHelper(root.right,max,root.val);
    }

    /**
     * 二叉树的中序遍历，不使用递归
     * 递归方法简单
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        inorderTraversalHelper(root,list);
        return list;
    }

    private void inorderTraversalHelper(TreeNode root, List<Integer> list) {
        if(root == null) return;
        inorderTraversalHelper(root.left,list);
        list.add(root.val);
        inorderTraversalHelper(root.right,list);
    }

    /**
     * 采用非递归方法
     * 迭代方法解决这个问题
     */
    public List<Integer> inorderTraversal_II(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.peek();
            if(node.left != null){
                stack.push(node.left);
                node.left = null;// 这里一定要切断，否则会将左边的值连起来
                continue;
            }
            list.add(stack.pop().val);
            if(node.right != null){
                stack.push(node.right);
            }
        }
        return list;
    }
}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    TreeNode(int x) {
        this.val = x;
    }
}
