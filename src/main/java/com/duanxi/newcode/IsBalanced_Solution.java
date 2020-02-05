package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/5 10:49
 * 牛客网：平衡二叉树
 * <p>
 * 题目：
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 */
public class IsBalanced_Solution {
    // 按照上一题寻找深度的题目思路，只需要查找左右子树两边高度不超过1即可
    // 需要判断的是左右子树的深度相差是否超过1
    // 超过1的话直接判定为false
    // 不超过1的话判定为true

    /**
     * 思路就是先判断总的，然后依次判断左右子树的高度差！
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        //
        if (root == null) return true;

        return Math.abs(getDepth(root.left) - getDepth(root.right)) <= 1
                && IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }

    // 深度肯定是要求解的
    public int getDepth(TreeNode root) {
        if (root == null) return 0;
        int lDepth = 1;
        int rDepth = 1;
        if (root.left != null) {
            lDepth += getDepth(root.left);
        }
        if (root.right != null) {
            rDepth += getDepth(root.right);
        }
        return Math.max(lDepth, rDepth);
    }

}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }
}
