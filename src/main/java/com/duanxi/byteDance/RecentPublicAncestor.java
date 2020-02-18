package com.duanxi.byteDance;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/2/18 21:04
 * 字节跳动：二叉搜索树的公共祖先（如果不用递归怎么实现？）
 * <p>
 * 题目：
 * <p>
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * 示例 2:
 * <p>
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * 说明:
 * <p>
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RecentPublicAncestor {
    /**
     * 注意由于是二叉搜索树，所以这里可以利用二叉搜索树的性质来解决
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;
        return helper(root, p, q);
    }

    private TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // 要不都大于
        if (p.val > root.val && q.val > root.val) {
            return helper(root.right, p, q);
            // 要不都小于
        } else if (p.val < root.val && q.val < root.val) {
            return helper(root.left, p, q);
        }
        // 要不就在两边,返回当前的
        return root;
    }

    /*public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // 要不都大于
        if(p.val > root.val && q.val > root.val){
            return lowestCommonAncestor(root.right,p,q);
            // 要不都小于
        }else if(p.val < root.val && q.val < root.val){
            return lowestCommonAncestor(root.left,p,q);
        }
        // 要不就在两边,返回当前的
        return root;
    }*/

    /**
     * 这是利用回溯的方法：
     * 查找左边，查找右边，最后看看中间，如果左+右+中为2或者>2，表示此时的root就是祖先
     * 否则的话返回三个中看是否找到大于一个，大于的话返回true，存在一个，否则返回false，一个都不存在
     */
    private TreeNode ans = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        findNode(root, p, q);
        return ans;
    }

    public boolean findNode(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        // 直接到最深处
        int left = findNode(root.left, p, q) ? 1 : 0;
        // 看看右边有没有找到
        int right = findNode(root.right, p, q) ? 1 : 0;
        // 记录中点
        int mid = (root.val == p.val || root.val == q.val) ? 1 : 0;
        // 找打了两个
        if (left + right + mid >= 2) {
            ans = root;
        }
        // 返回是否找到的结果
        return (left + right + mid) > 0;
    }
    // 利用map记录节点和指针。利用队列实现广度遍历
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 用以放置节点和父指针
        Map<TreeNode,TreeNode> parent = new HashMap<>();
        parent.put(root,null);
        stack.push(root);
        // 如果同时包含两个Node的话终止
        while(!parent.containsKey(p) || !parent.containsKey(q)){
            TreeNode pop = stack.pop();
            if(pop.left != null){
                parent.put(pop.left,pop);
                stack.push(pop.left);
            }
            if(pop.right != null){
                parent.put(pop.right,pop);
                stack.push(pop.right);
            }
        }
        // 这主要是对祖先节点去重
        Set<TreeNode> ancestors = new HashSet<>();
        // 从上到下取出得到的所有的祖先节点，还不带重复
        while(p != null){
            ancestors.add(p);
            p = parent.get(p);
        }
        // 获取共同的祖先节点
        while( !ancestors.contains(q)){
            // 从上到下获取父节点
            q = parent.get(q);
        }
        return q;
    }


}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    public TreeNode(int x) {
        this.val = x;
    }
}
