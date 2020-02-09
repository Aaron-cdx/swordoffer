package com.duanxi.newcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/2/9 14:20
 * 牛客网：序列化二叉树
 * <p>
 * 题目：
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 * <p>
 * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，
 * 从而使得内存中建立起来的二叉树可以持久保存。
 * 序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，
 * 序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），
 * 以 ！ 表示一个结点值的结束（value!）。
 * <p>
 * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 */
public class SerAndDeserTree {
    /**
     * 1
     * 2   3
     * 4 5 6 7
     * 序列化结果：先序
     * 1!2!4!#!#!5!#!#!3!6!#!#!7!#!#!
     */
    String Serialize(TreeNode root) {
        if (root == null) return "";
        return helpSerialize(root, new StringBuilder()).toString();
    }

    private StringBuilder helpSerialize(TreeNode root, StringBuilder s) {
        if (root == null) return s;
        s.append(root.val).append("!");
        if (root.left != null) {
            helpSerialize(root.left, s);
        } else {
            s.append("#!");
        }
        if (root.right != null) {
            helpSerialize(root.right, s);
        } else {
            s.append("#!");
        }
        return s;
    }

    /**
     * 这里反序列化有两种方式:
     * 第一种是将String[]数组转换为list，每次遇见就删除一个
     * 第二种是设置全局index，每次遇见就index++,相当移除元素操作
     */
    private int index = 0;

    TreeNode Deserialize(String str) {
        if (str == null || str.length() == 0) return null;
        String[] split = str.split("!");
        return helpDeserialize(split);
    }

    private TreeNode helpDeserialize(String[] strings) {
        if (strings[index].equals("#")) {
            index++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(strings[index]));
        index++;
        root.left = helpDeserialize(strings);
        root.right = helpDeserialize(strings);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        SerAndDeserTree test = new SerAndDeserTree();
        String serialize = test.Serialize(root);
        System.out.println("序列化结果: " + serialize);
    }
}
