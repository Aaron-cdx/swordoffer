package com.duanxi.byteDance;

/**
 * @author caoduanxi
 * @Date 2020/3/10 22:31
 * 字节跳动：重建二叉树
 * <p>
 * 题目：
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */
public class ReConstruceBinaryTree {
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return constructTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    private TreeNode constructTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        if (preStart > preEnd) return null;
        // prestart作为根节点
        TreeNode root = new TreeNode(pre[preStart]);
        // 现在要找到左边第一个节点
        int index = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (in[i] == pre[preStart]) {
                index = i - inStart;
                break;
            }
        }
        // 左边就是从起点开始+1，到起点+index截止。而此时对应的中序遍历为开始到开始+index-1，因为index+开始就是根节点了
        root.left = constructTree(pre, preStart + 1, preStart + index, in, inStart, inStart + index - 1);
        // 右边的话就是从起点开始+index+1，后半部分。而对应的中序遍历为中节点+1，到结束
        root.right = constructTree(pre, preStart + index + 1, preEnd, in, inStart + index + 1, inEnd);
        return root;
    }
}
