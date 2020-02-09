package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/9 9:48
 * 牛客网：二叉树的下一个节点
 * <p>
 * 题目：
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class GetNext {
    class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    /**
     *        a
     *      /  \
     *     b    c
     *   / \   / \
     *  d  e  f  g
     *    / \
     *   h   i
     * 分析情况：中序遍历结果：d b h e i a f c g
     * 1. 如果pHead=null,毫无疑问，返回null
     * 2. 如果一个节点有右子树，那么它的下一个节点为右子树的最左节点
     * 3. 如果一个节点没有左右子树，且为父节点的左子树，那么它的下一个节点为父节点
     * 4. 如果一个节点既没有左右子树，且还是父节点的右子树，此时有两种情况
     * 1) 如果为i节点的话，下一个节点时a,此时需要顺着父节点向上遍历
     * 如果找到自己的父节点是根节点的左子树，则下一个节点是根节点
     * 2) 如果为g节点的话，下一个节点是null，此时顺着父节点向上遍历到了a
     * a没有父节点，返回null
     */

    // 注意题目的意思需要找出特定节点的下一个，自己在脑海中假象当前有一个被给出的节点
    // 然后我们来求它下一个节点
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) return null;
        TreeLinkNode pNext = null;
        // 左右节点需要分情况讨论
        if (pNode.right != null) {
            // 跳到右子树，找最左节点即可
            TreeLinkNode tempNode = pNode.right;
            while (tempNode.left != null) {
                tempNode = tempNode.left;
            }
            pNext = tempNode;
            // 如果没有右子树，不能根据左右子树是否为空来查找
        } else if (pNode.next != null) {
            /*// 如果是i呢,左右子树都为空，此时根据父节点查找的话，
            // 一直向上遍历，知道找到a
            TreeLinkNode tempNode = pNode.next;
            // 这里需要分情况，看看是左子树还是右子树
            if(pNode == tempNode.left){
                pNext = tempNode;
            }else{
                // 右子树的情况
                while(tempNode.next != null){
                    tempNode = tempNode.next;
                    pNode = pNode.next;
                }
                // 如果当前的左子树为当前节点，此时下一个节点就出来了
                if(tempNode.left == pNode){
                    pNext = tempNode;
                }
            }*/
            TreeLinkNode pCur = pNode; // 当前
            TreeLinkNode tempNode = pNode.next; // 父节点
            // tempNode一定要比pCur多走一步
            while (tempNode != null && pCur == tempNode.right) {
                pCur = tempNode;
                tempNode = pCur.next;
            }
            pNext = tempNode;
        }
        return pNext;
    }
}
