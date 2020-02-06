package com.duanxi.newcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/2/6 9:44
 * 牛客网：和为S的连续正数序列
 * <p>
 * 题目：
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,
 * 他马上就写出了正确答案是100。但是他并不满足于此,
 * 他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
 * 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 * <p>
 * 输出描述：
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，
 * 序列间按照开始数字从小到大的顺序
 */
public class FindContinuousSequence {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        /**
         * 第一种解法：
         * 思想：遍历所有的整数。只要整数<sum就一直遍历下去
         *      每次到达这个数的时候，内部循环往后遍历是否一直相加等于，是的话就纳入进入
         */
        /*for (int i = 1; i <= sum; i++) {
            int cur = 0;
            int j = i;
            while (cur < sum) {
                cur += j++;
                if (cur == sum) {
                    break;
                }
            }
            ArrayList<Integer> list = new ArrayList<>();
            if (cur == sum && j - i > 1) {
                for (int k = i; k < j; k++) {
                    list.add(k);
                }
                // 注意题目要求的是连续的正整数序列，如果只有一个就不加入
                res.add(list);
            }
        }*/
        /**
         * 第二种解法：利用双指针前进
         */
        /*int l = 1;
        int r = 2;
        while (l < r) {
            // 连续的正整数
            int s = (r + l) * (r - l + 1) / 2;
            if (s < sum) {
                r++;
            }
            if (s > sum) {
                l++;
            }
            if (s == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = l; i <= r; i++) {
                    list.add(i);
                }
                res.add(list);
                r++;
            }
        }*/
        /**
         * 第三种解法：参考剑指offer的思想
         *
         * 与自己的第一种方法类似，但是此方法
         * 直接使用双指针，会比较方便。
         */
        if (sum < 3) return res;

        int l = 1;
        int r = 2;
        int curSum = 3;
        int mid = (sum + 1) / 2; // 计算的是sum的中点
        while (l < mid) {
            while (curSum < sum) {
                r++;
                // 计算值向sum逼近
                curSum += r;
            }
            if (curSum == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = l; i <= r; i++) {
                    list.add(i);
                }
                res.add(list);
            }
            // 不小于 不等于 只剩下大于了
            curSum -= l;
            l++;
        }
        return res;
    }

    public static void main(String[] args) {
        FindContinuousSequence test = new FindContinuousSequence();
        ArrayList<ArrayList<Integer>> arrayLists = test.FindContinuousSequence(100);
        for (ArrayList<Integer> arrayList : arrayLists) {
            System.out.println(arrayList);
        }
    }
}
