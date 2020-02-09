package com.duanxi.newcode;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/2/9 11:11
 * 牛客网：按照之字形顺序打印二叉树
 * <p>
 * 题目：
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 * <p>
 * 思路：
 * 本题的主要目的是将需要获取的值按照正反依次存储起来
 * 只是数据的顺序变更，数据还是这么些数据，只是取值方式变了
 * <p>
 * 可以使用栈，也可以使用队列依次获取，从前往后，设立标志位。
 */
public class PrintZigzagTree {
    /**
     * 剑指offer上的解法，使用两个栈来实现
     * 第一个栈s1存储奇数层的值打印，然后判断中使用s2存储下一层的值因为要逆序
     * 所以作为栈正着存就好从left->right
     * <p>
     * 第二个栈s2存储偶数层的值打印，然后判断中使用s1存储下一次的值，因为要正序了
     * 此时作为栈反着存就好right->left
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;
        int degree = 1;
        // 存储奇数层
        Stack<TreeNode> s1 = new Stack<>();
        s1.push(pRoot);
        // 偶数层
        Stack<TreeNode> s2 = new Stack<>();
        while (!s1.isEmpty() || !s2.isEmpty()) {
            // 表示奇数层 s1层记录
            if (degree % 2 != 0) {
                ArrayList<Integer> list = new ArrayList<>();
                while (!s1.isEmpty()) {
                    TreeNode popNode = s1.pop();
                    if (popNode != null) {
                        list.add(popNode.val);
                        // 奇数层下面就是偶数层，s2记录
                        if (popNode.left != null) s2.push(popNode.left);
                        if (popNode.right != null) s2.push(popNode.right);
                    }
                }
                if (!list.isEmpty()) {
                    res.add(list);
                    degree++;
                }
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                while (!s2.isEmpty()) {
                    TreeNode popNode = s2.pop();
                    if (popNode != null) {
                        list.add(popNode.val);
                        // 奇数层下面就是偶数层，s2记录
                        if (popNode.right != null) s1.push(popNode.right);
                        if (popNode.left != null) s1.push(popNode.left);
                    }
                }
                if (!list.isEmpty()) {
                    res.add(list);
                    degree++;
                }
            }
        }
        return res;
    }
    /**
     * 先将需要打印的取出，然后再存值
     */
    /*public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;
        boolean leftToRight = true;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            if(leftToRight){
                for (int i = 0; i < size; i++) {
                    list.add(queue.get(i).val);
                }
            }else{
                for (int i= size-1; i>= 0; i--){
                    list.add(queue.get(i).val);
                }
            }
            leftToRight = !leftToRight;
            res.add(list);
            // 再来处理存值操作
            while(size != 0){
                TreeNode poll = queue.poll();
                if (poll.left != null) queue.add(poll.left);
                if (poll.right != null) queue.add(poll.right);
                size--;
            }
        }
        return res;
    }*/
    /**
     * 第一种方法没看懂
     */
    /*public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;
        LinkedList<TreeNode> list = new LinkedList<>();
        ArrayList<Integer> tempList = new ArrayList<>();
        // 分隔符
        list.offerLast(null);
        list.offerLast(pRoot);
        boolean leftToRight = true;
        // 奇数层正序遍历，偶数层倒序遍历
        while (list.size() != 1) {
            TreeNode treeNode = list.removeFirst();
            // 表示到达分隔符
            if (treeNode == null) {
                Iterator<TreeNode> iter = null;
                if (leftToRight) {
                    // 从左到右
                    iter = list.iterator();
                } else {
                    // 从右到左
                    iter = list.descendingIterator();
                }
                // 取反
                leftToRight = !leftToRight;

                while(iter.hasNext()){
                    TreeNode node = iter.next();
                    tempList.add(node.val);
                }
                res.add(new ArrayList<>(tempList));
                tempList.clear();
                // 继续添加分隔符
                list.offerLast(null);
                continue;
            }
            if(treeNode.left != null){
                list.offerLast(treeNode.left);
            }
            if(treeNode.right != null){
                list.offerLast(treeNode.right);
            }
        }
        return res;
    }*/

    /*private void helper(TreeNode pRoot, int degree) {
        if (pRoot == null) return;
        res.add(degree, new ArrayList<>());
        res.get(degree).add(0,pRoot.val);
        degree++;
        if (degree % 2 == 0) {
            if (pRoot.right != null) {
                helper(pRoot.right, degree);
            }
            if (pRoot.left != null) {
                helper(pRoot.left, degree);
            }
        } else {
            if (pRoot.left != null) {
                helper(pRoot.left, degree);
            }
            if (pRoot.right != null) {
                helper(pRoot.right, degree);
            }
        }
    }*/
}
