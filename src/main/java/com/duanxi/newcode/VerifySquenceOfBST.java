package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/1/9 15:05
 * 二叉搜索树的后序遍历序列(不存在相同的数)
 * <p>
 * 判断是否是二叉搜索树的后序遍历序列
 */
public class VerifySquenceOfBST {
    // 这里需要判断单纯的左子树
    // 单纯的右子树，或者左右子树
    public static boolean verifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) return false;
        return verify(sequence, 0, sequence.length - 1);
    }

    private static boolean verify(int[] sequence, int start, int end) {
        if (start >= end) return true;
        int index1 = end;
        // 这里是定位到右子树的最后一个点
        while (index1 > start && sequence[index1 - 1] > sequence[end]) {
            index1--;// 找打了左子树的跟节点
        }
//        index1 --;
        // 这里定位刚好是左子树的最后一个点
        for (int i = start; i < index1 - 1; i++) {
            // index1的左边第一个为右边节点
            if (sequence[i] > sequence[end]) {
                return false;
            }
        }
        return verify(sequence, start, index1 - 1) && verify(sequence, index1, end - 1);
        // 如果上面是到index1不--的话，就用这个
//        return verify(sequence, start, index1) && verify(sequence, index1+1, end-1);
    }

    public static void main(String[] args) {
        System.out.println(verifySquenceOfBST(new int[]{2, 4, 3, 6, 8, 7, 5}));
        System.out.println(verifySquenceOfBST(new int[]{7, 4, 5, 6}));
//        System.out.println(verifySquenceOfBST(new int[]{5,4,3,2,1}));
    }
}
