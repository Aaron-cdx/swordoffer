package com.duanxi.newcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author caoduanxi
 * @Date 2020/2/9 13:24
 * 牛客网：把二叉树打印成多行
 * <p>
 * 题目：
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行
 */
public class PrintTreeNode {
    /**
     * 第一种方法：使用队列来处理
     */
    public ArrayList<ArrayList<Integer>> Print1(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int size = queue.size();
            while (size != 0) {
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if (poll.left != null) queue.add(poll.left);
                if (poll.right != null) queue.add(poll.right);
                size--;
            }
            res.add(list);
        }
        return res;
    }

    /**
     * 使用递归的解法
     */
    private ArrayList<ArrayList<Integer>> res = new ArrayList<>();

    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        if (pRoot == null) return res;
        helper(pRoot, 1);
        return res;
    }

    private void helper(TreeNode pRoot, int degree) {
        if (pRoot == null) return;
        // 这里应该要分条件创建ArrayList否则取值会出现问题
        if (res.size() < degree) {
            // 如果层数大于list中数量，添加新的存储list
            res.add(new ArrayList<>());
        }
        res.get(degree - 1).add(pRoot.val);
        // 必须先加否则还是原数据进入
        helper(pRoot.left, degree + 1);
        helper(pRoot.right, degree + 1);
    }
}
