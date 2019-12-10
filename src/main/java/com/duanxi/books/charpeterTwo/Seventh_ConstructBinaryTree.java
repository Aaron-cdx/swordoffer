package com.duanxi.books.charpeterTwo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author caoduanxi
 * @2019/12/9 21:41
 * 剑指offer 面试题7 重建二叉树
 * <p>
 * 题目：输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历都不含有重复的数字，
 * 例如输入的前序遍历序列为{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}.
 */
public class Seventh_ConstructBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            this.val = x;
        }
    }

    private static TreeNode constructBinaryTree(int[] preOrder, int[] inOrder) {
        if (preOrder == null || inOrder == null) return null;
        int len = inOrder.length;
//        return buildTree(preOrder, inOrder, 0, len - 1, 0, len - 1);
        return rebuildTree(preOrder, inOrder, 0, len - 1, 0, len - 1);
    }

    // 使用递归的方法构造二叉树
    private static TreeNode buildTree(int[] preOrder, int[] inOrder, int preStart, int preEnd, int inStart, int inEnd) {
        TreeNode root = new TreeNode(preOrder[preStart]);
        // 如果只剩下一个元素的话，此时就是等于了，所以需要使用大于区分最后一个元素
        if (preStart > preEnd) return null;
        // 遍历找到这个preOrder[start]的值为多少
        int index = inStart;
        for (int i = inStart; i <= inEnd; i++) {
            if (inOrder[i] == preOrder[preStart]) {
                index = i;
            }
        }
        // 这是获取元素的个数，好让前序遍历区分哪是前序的，哪是后序的
        int leftLength = index - inStart;
        int leftPreEnd = preStart + leftLength;

        if (leftLength > 0) {
            root.left = buildTree(preOrder, inOrder, preStart + 1, leftPreEnd, inStart, index - 1);
        }
        if (leftLength < preEnd - preStart) {
            root.right = buildTree(preOrder, inOrder, leftPreEnd + 1, preEnd, index + 1, inEnd);
        }

        return root;
    }

    private static TreeNode rebuildTree(int[] preOrder, int[] inOrder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd) return null;
        // 构造一个根节点
        TreeNode root = new TreeNode(preOrder[preStart]);

        // 找到在中序中的索引
        int index = inStart;
        for (int i = inStart; i <= inEnd; i++) {
            if (inOrder[i] == preOrder[preStart]) {
                index = i;
            }
        }
        // 记录元素的长度，注意是inStart开始
        int numLength = index - inStart;
        int leftEnd = preStart + numLength;
        // 表明左边是有元素的否则  1 2 4 7 | 4 7 1 2当搜寻到4的时候，此时得inStart在3而index在0位置，此时可以说明没有左节点
        // 这里不用判断，因为只要preStart > preEnd的时候，直接返回null
        // 如果这里没有右节点的话，此时4 7已经是左边了此时搜寻到4，查找的话index=0，numLength=0;leftEnd=preStart;preStart=leftEnd+1
        // 可得preStart > preEnd,得到右节点为null
//        if (numLength > 0) {
        root.left = rebuildTree(preOrder, inOrder, preStart + 1, leftEnd, inStart, index - 1);
//        }
        // 需要判断是否有右节点,只需要确保preStart不超过preEnd即可
//        if (leftEnd - preStart > numLength) {
        root.right = rebuildTree(preOrder, inOrder, leftEnd + 1, preEnd, index + 1, inEnd);
//        }
        return root;
    }

    public static void main(String[] args) {
        int[] preOrder = new int[]{1, 2, 4, 7, 3, 5, 6, 8};
        int[] inOrder = new int[]{4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode treeNode = constructBinaryTree(preOrder, inOrder);
//        TreeNode treeNode = rebuildTree(preOrder, inOrder);
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(treeNode);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            list.add(temp.val);
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
        System.out.println(list);
    }
}
