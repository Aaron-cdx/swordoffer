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
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
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
}
