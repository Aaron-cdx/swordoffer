package com.duanxi.video.tree;

import java.util.ArrayList;

/**
 * @author caoduanxi
 * @Date 2020/1/9 15:54
 * 二叉树中和为某一路径的路径
 * 题目描述
 * <p>
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class FindPath {


    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        ArrayList<Integer> tempList = new ArrayList<>();
        getPath(root, target, tempList, list);
        return list;
    }

    public void getPath(TreeNode root, int target, ArrayList<Integer> tempList, ArrayList<ArrayList<Integer>> list) {
        if (root == null) return;
        // 添加再说
        tempList.add(root.val);
        // 如果左右为空，且值相等，则直接添加然后返回即可
        if (root.left == null && root.right == null && target == root.val) {
            // 每一个构造自己单独的list,还是要继续运算
            list.add(new ArrayList<>(tempList));
        }else{
            // 否则左右遍历，获取所有的路径
            getPath(root.left, target - root.val, tempList, list);
            getPath(root.right, target - root.val, tempList, list);
        }
        tempList.remove(tempList.size() - 1);
    }
}
