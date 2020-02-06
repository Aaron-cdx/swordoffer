package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/6 14:45
 * 牛客网：圆圈中最后剩下的数
 * <p>
 * 题目：
 * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,
 * 今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。
 * 其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,
 * 他随机指定一个数m,让编号为0的小朋友开始报数。
 * 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,
 * 并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....
 * 直到剩下最后一个小朋友,可以不用表演,
 * 并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。请
 * 你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
 * <p>
 * 如果没有小朋友，请返回-1
 */
public class LastRemainingNumber {
    public int LastRemaining_Solution(int n, int m) {
        // 两者中任一为0直接返回-1
        if (n < 1 || m < 1) return -1;
        // 模拟链表成环运算
        int[] arr = new int[n];
        int count = n; // 孩子总数
        int i = -1; // 记录一直走的下标位置
        int step = 0; // 记录孩子们的报数
        // 前提是孩子们需要存在
        while (count > 0) {
            // 首先是第一个孩子
            i++;
            // 到底了，从头来，类似链表
            if (i >= n) i = 0;
            // 如果这个孩子被剔除，直接跳过
            if (arr[i] == -1) continue;
            step++;
            // 报数到达了m的话，也就是真实报数到达m-1
            if (step == m) {
                // 需要将这个孩子置为-1，同时从新开始计数，且剔除一名孩子
                arr[i] = -1;
                step = 0;
                count--;
            }
        }
        // 最后返回的留下来的孩子的下标
        return i;
    }

    public static void main(String[] args) {
        LastRemainingNumber test = new LastRemainingNumber();
        int i = test.LastRemaining_Solution(5, 2);
        System.out.println(i);

    }

}
