package com.duanxi.byteDance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/3/15 22:04
 * 字节跳动：二叉树和为某一值的路径
 * <p>
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
 * 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 * 返回:
 * <p>
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryTreePathSum {
    /**
     * 注意这里需要先明确是一条到达叶子节点的完整路径，
     * 叶子节点也就是左右都为null，且sum为0才可以被加入
     */
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return res;
        getPathSum(root, sum,new ArrayList<>());
        return res;
    }

    //  list从哪里来赋值呢? 感觉没办法获取到任何值
    private void getPathSum(TreeNode root, int sum, List<Integer> list) {
        if (root == null) return;
        if(list == null){
            list = new ArrayList<>();
        }
        list.add(root.val);
        sum -= root.val;
        // 只有为叶子节点的时候且sum=0时，才添加
        if (root.left == null && root.right == null && sum == 0) {
            res.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        getPathSum(root.left, sum,list);
        getPathSum(root.right, sum,list);
        list.remove(list.size() - 1);
    }
}
