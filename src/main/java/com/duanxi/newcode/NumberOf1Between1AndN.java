package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/1/16 21:54
 * 整数中1的出现次数（从1~n的整数中1的出现次数）
 * <p>
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
 * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
 * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 */
public class NumberOf1Between1AndN {
    public int NumberOf1Between1AndN_Solution(int n) {
        // 第一种方法就是每个元素都判断，获取总的1的和，这个时间复杂度太高O(nlogn)
        // 具体的解法还是没有想出来！
        // 例如534
        // 首先按照所有的个位也即base=1来算1的个数，首先4是个位，此时如果要是个位到1，只能是0-9满了
        // 所以534需要经历53轮，所以会出现53个1，然后由于末尾4中大于1，所以还需要加上这一次的1
        // 然后就定位到base=10算十位的，此时到了3，十位的话，需要0-10才能够凑成一个，所以，可以知道经过了5轮
        // 也就是5*10 = 50，此时由于3大于1，表名3中还是有1，所以需要+10个
        // 如果是等于1的话，此时就需要利用n这个1后面的个位取出来，因为个位的话，可以通过十位上的1构造出个位上数字大小的含有1的值
        // 然后继续base=100,此时就是算百位了，此时没有下一轮，所以之间判断当前的值是否为1
        // 为1的话取出10位上的大小，如果是大于1的话，表示从1-2....这个值，一定会经历base个1，所以直接+100即可
        if (n < 1) {
            return 0;
        }
        int count = 0;
        int base = 1;
        int round = n;
        while (round > 0) {
            // 首先判断当前位的值
            int weight = round % 10;
            round /= 10;// 下一轮
            count += round * base;
            if (weight == 1) {
                // 如果当前位置数为1的话
                count += (n % base) + 1;
            } else if (weight > 1) {
                count += base;
            }
            base *= 10;
        }

        return count;
    }

    private int powerBaseOfN(int n) {
        return (int) Math.pow(10, n);
    }

    public int getNumLen(int num) {
        int len = 0;
        while (num != 0) {
            len++;
            num /= num;
        }
        return len;
    }

//    public static void main(String[] args) {
//        NumberOf1Between1AndN_Solution solution = new NumberOf1Between1AndN_Solution();
//        System.out.println(solution.NumberOf1Between1AndN_Solution(534));
//    }

}
