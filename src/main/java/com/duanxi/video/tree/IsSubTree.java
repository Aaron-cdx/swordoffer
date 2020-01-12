package com.duanxi.video.tree;


/**
 * @author caoduanxi
 * @Date 2020/1/8 21:18
 * leetcode 572 另一个树的子树
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 * <p>
 * 示例 1:
 * 给定的树 s:
 * <p>
 * 3
 * / \
 * 4   5
 * / \
 * 1   2
 * 给定的树 t：
 * <p>
 * 4
 * / \
 * 1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 * <p>
 * 示例 2:
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsSubTree {
    // 这种树只有三种情况，一种是全等，一种是左子树，一种是右子树
    // 自己刚刚的错误是因为把全等的情况都放在一种方法下讨论，所以会出现错误
//    public boolean isSubtree(TreeNode s, TreeNode t) {
//        if (s == null && t == null) return true;
//        if (s == null || t == null) return false;
//        return isSameTree(s, t)
//                || isSubtree(s.right, t)
//                || isSubtree(s.left, t);
//    }
//
//    // 完全相同的树，或者是左子树，或者是右子树
//    public boolean isSameTree(TreeNode s, TreeNode t) {
//        if (s == null && t == null) return true;
//        if (s == null || t == null) return false;
//        return s.val == t.val && isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
//    }

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        return isSameTree(root1, root2)
                || HasSubtree(root1.left, root2)
                || HasSubtree(root1.right, root2);
    }

    public boolean isSameTree(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        return root1.val == root2.val && isSameTree(root1.right, root2.right) && isSameTree(root1.left, root2.left);
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        this.val = x;
    }
}
