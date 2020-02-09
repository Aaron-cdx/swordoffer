package com.duanxi.newcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author caoduanxi
 * @Date 2020/2/9 16:40
 * 牛客网：数据流中的中位数
 * <p>
 * 题目：
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，
 * 那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，
 * 那么中位数就是所有数值排序之后中间两个数的平均值。
 * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数
 */
public class GetMedianOfStream {
    /**
     * 整体的思路就是保持两个优先队列
     * <p>
     * 你想：需要求的是中位数，如果我将 1 2 3 4 5 6 7 8
     * 此时中位数为 (4+5)/2=4.5
     * 怎么计算的就是后半段的最小值5与前半段的最大值4相加求平均
     * <p>
     * 所以使用小顶堆存储大的数，这样小顶堆只要吐数就是后半段最小的数
     * 使用大顶堆存储小的数，这样小顶堆只要吐数就是前半段最大的数
     * <p>
     * 每次求中位数，如果是奇数由于存储在大顶堆，直接返回大顶堆的那个数即可
     * 如果是偶数，则是大顶堆和小顶堆吐出的数求均值
     */
    // 可以使用插入排序动态的获取个数
    private int cnt = 0;
    private PriorityQueue<Integer> low = new PriorityQueue<>();
    // 默认维护小顶堆
    private PriorityQueue<Integer> high = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });

    public void Insert(Integer num) {
        // 数量++
        cnt++;
        // 如果为奇数的话
        if ((cnt & 1) == 1) {
            // 由于奇数，需要存放在大顶堆上
            // 但是呢，现在你不知道num与小顶堆的情况
            // 小顶堆存放的是后半段大的数
            // 如果当前值比小顶堆上的那个数更大
            if (!low.isEmpty() && num > low.peek()) {
                // 存进去
                low.offer(num);
                // 然后在将那个最小的吐出来
                num = low.poll();
            }
            high.offer(num);
        } else {
            // 偶数的话，此时需要存放的是小的数
            // 注意无论是大顶堆还是小顶堆，吐出数的前提是得有数
            if (!high.isEmpty() && num < high.peek()) {
                high.offer(num);
                num = high.poll();
            }
            low.offer(num);
        }

    }

    public Double GetMedian() {// 表明是偶数
        double res = 0;
        // 奇数
        if ((cnt & 1) == 1) {
            res = high.peek();
        } else {
            res = (high.peek() + low.peek()) / 2.0;
        }
        return res;
    }

    public static void main(String[] args) {
        GetMedianOfStream test = new GetMedianOfStream();
        test.Insert(5);
        System.out.println(test.GetMedian());
        test.Insert(2);
        System.out.println(test.GetMedian());
        test.Insert(3);
        System.out.println(test.GetMedian());
        test.Insert(4);
        System.out.println(test.GetMedian());
        test.Insert(1);
        System.out.println(test.GetMedian());
        test.Insert(6);
        System.out.println(test.GetMedian());
        test.Insert(7);
        System.out.println(test.GetMedian());
        test.Insert(0);
        System.out.println(test.GetMedian());
        test.Insert(8);
        System.out.println(test.GetMedian());


    }
}
