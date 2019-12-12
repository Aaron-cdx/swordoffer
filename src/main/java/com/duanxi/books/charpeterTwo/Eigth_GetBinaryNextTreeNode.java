package com.duanxi.books.charpeterTwo;

/**
 * @author caoduanxi
 * @2019/12/10 21:38
 * 剑指offer 面试题8 二叉树的下一个节点
 * <p>
 * 题目：给定一颗二叉树和其中一个节点，如何找出中序遍历序列的下一个节点？
 * 树中的节点除了有两个分别指向左、右子节点的指针，还有一个指向父节点的指针。
 * <p>
 * 这一题主要是分析清楚所有的情况，怎么样的点有下一个节点以及按照中序的话，下一个节点是怎么查找的
 * 利用父节点如何一直查找。
 */
public class Eigth_GetBinaryNextTreeNode {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int x) {
            this.val = x;
        }
    }

    /**
     * 分析：
     * 如果当前节点有右子树，则它的下一个节点就是它的右子树的最左节点
     * <p>
     * 如果当前节点没有右子树，且它是它的父节点的左边节点，那么它的下一个节点就是父节点
     * <p>
     * 如果当前节点没有右子树，且还是他父节点的右边节点，那么它就要沿着父节点一直向上遍历
     * 直到找到一个节点是它父节点的左节点，这样下一个节点就是当前节点的父节点
     * 如果没有找到，表名当前的点是最后一个点，没有父节点为null
     */
    private static TreeNode getBinaryNextTreeNode(TreeNode treeNode, TreeNode node) {
        TreeNode nextNode = null;
        if (treeNode == null || node == null) return null;

        if (node.right != null) {
            TreeNode rightTree = node.right;
            while (rightTree.left != null) {
                rightTree = rightTree.left;
            }
            nextNode = rightTree;
        }
        // 表名当前节点没有右子树,父节点不为空的话
        else if (node.parent != null) {
            TreeNode curNode = node;
            TreeNode parent = node.parent;
            // 如果是父节点的左节点的话，下一个节点就是父节点
            /*if(parent.left == node){
                nextNode = parent;
            }else{
                // 否则的话，就是右节点了，此时需要一直往上遍历
                while(parent.parent != null){

                }
            }*/
            // 利用递归向上遍历
            while (parent != null && curNode == parent.right) {
                curNode = parent;
                parent = parent.parent;
            }
            nextNode = parent;
        }
        return nextNode;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.parent = root;
        root.right = new TreeNode(3);
        root.right.parent = root;
        root.left.left = new TreeNode(4);
        root.left.left.parent = root.left;
        root.left.right = new TreeNode(5);
        root.left.right.parent = root.left;
//        root.left.right.left = new TreeNode(8);
//        root.left.right.right = new TreeNode(9);
        root.right.left = new TreeNode(6);
        root.right.left.parent = root.right;
        root.right.right = new TreeNode(7);
        root.right.right.parent = root.right;

        TreeNode binaryNextTreeNode = getBinaryNextTreeNode(root, root.right);
        System.out.println(binaryNextTreeNode);
    }
}
