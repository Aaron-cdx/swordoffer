package com.duanxi.newcode;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/2/9 15:27
 * 牛客网：二叉搜索树的第k个结点
 * <p>
 * 题目：
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。
 * 例如，(5，3，7，2，4，6，8)中，按结点数值大小顺序第三小结点的值为4。
 */
public class KthNode {
    private int count = 0;

    /**
     * 第一种方法：中序遍历获取顺序，计数，找到第k个即可
     */
    TreeNode KthNode1(TreeNode pRoot, int k) {
        // 本来准备使用最小堆来维护，不行，数量不能控制
        /*// 如果pRoot只有7个结点，但是k有8,直接返回null
        if (pRoot == null || k < 1) return null;
        Queue<TreeNode> queue = new PriorityQueue<>(k, new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode o1, TreeNode o2) {
                return o1.val - o2.val;
            }
        });
        Queue<TreeNode> list = new LinkedList<>();
        list.add(pRoot);

        while (!list.isEmpty()) {
            int size = list.size();
            while (size != 0) {
                TreeNode poll = list.poll();
                queue.add(poll);
                if (queue.size() > k) {
                    queue.poll();
                }
                if (poll.left != null) list.add(poll.left);
                if (poll.right != null) list.add(poll.right);
                size--;
            }
        }
        return queue.poll();*/
        // 按照中序遍历会是一个排序好的数
        if (pRoot != null) {
            // 先找左边
            TreeNode treeNode = KthNode(pRoot.left, k);
            // 表明左边找到了第k小的点，直接返回
            if (treeNode != null) {
                return treeNode;
            }
            count++;
            if (count == k) return pRoot;
            treeNode = KthNode(pRoot.right, k);
            // 同上，表示右边找到了
            if (treeNode != null) {
                return treeNode;
            }
        }
        return null;
    }

    /**
     * 第二种方法：使用栈结构来
     * 栈结构只保存左边的，包括根节点，到了最上面直接转到right
     * 然后到最左。
     * <p>
     * 所有的操作都是基于pRoot，复用pRoot很精辟
     */
    TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null || k < 1) return null;
        Stack<TreeNode> stack = new Stack<>();
        int count = 0;
        while (pRoot != null || !stack.isEmpty()) {
            if (pRoot != null) {
                stack.push(pRoot);
                // 一直向左模拟中序遍历
                pRoot = pRoot.left;
            } else {
                // 到了最左边
                pRoot = stack.pop();
                count++;
                if (count == k) return pRoot;
                pRoot = pRoot.right;
            }
        }
        return null;
    }

}
