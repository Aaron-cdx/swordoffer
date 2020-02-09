package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/9 10:53
 * 牛客网：对称的二叉树
 * <p>
 * 题目：
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
public class IsSymmetrical {
    // 判断是否是对称的二叉树
    // 注意这里判断的是自己与自己是否是对称二叉树
    boolean isSymmetrical(TreeNode pRoot) {
        return helper(pRoot, pRoot);
    }

    // 是对其左右子树判断
    private boolean helper(TreeNode lRoot, TreeNode rRoot) {
        // 如果左右同时为null，直接返回true
        if (lRoot == null && rRoot == null) return true;
        // 如果左右只有一边为null，直接返回false
        if (lRoot == null || rRoot == null) return false;
        // 否则两边都不为null，则判定是否值相等，不等直接返回false
        // 否则继续交替判断
        /*if (lRoot.val == rRoot.val) {
            return helper(lRoot.left, rRoot.right)
                    && helper(lRoot.right, rRoot.left);
        }
        return false;*/
        return (lRoot.val == rRoot.val) && helper(lRoot.left, rRoot.right)
                && helper(lRoot.right, rRoot.left);
    }

}
