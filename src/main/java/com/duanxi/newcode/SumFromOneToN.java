package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/7 10:21
 * 牛客网：求1+2+3+.....+n
 * <p>
 * 题目：
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、
 * if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class SumFromOneToN {
    /**
     * 思路：因为不能使用循环，所以只有使用递归
     * 因为不能使用判断或者选择语句，只能选择短路与
     * 因为递归需要终止条件，所以使用短路与获取终止条件
     * 确保递归能够开始和结束
     */
    // 题目标签是进制转换
    // 不能用while循环用递归来解决问题
    public int Sum_Solution(int n) {
        int ans = n;
        // 用逻辑与短路的操作来获取值相加的结果
        // 递归需要一个终止条件，这里缺少了终止条件
        boolean flag = (ans != 0) && (ans += Sum_Solution(n - 1)) != 0;
        return ans;
    }

    public static void main(String[] args) {
        SumFromOneToN test = new SumFromOneToN();
        int a = test.Sum_Solution(5);
        System.out.println(a);
    }
}
