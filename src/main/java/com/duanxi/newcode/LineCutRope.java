package com.duanxi.newcode;

/**
 * @author caoduanxi
 * @Date 2020/2/10 13:05
 * 牛客网：剪绳子
 * <p>
 * 题目：
 * 给你一根长度为n的绳子，请把绳子剪成整数长的m段
 * （m、n都是整数，n>1并且m>1），每段绳子的长度
 * 记为k[0],k[1],...,k[m]。请问k[0]xk[1]x...xk[m]
 * 可能的最大乘积是多少？例如，当绳子的长度是8时，
 * 我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * <p>
 * 输入描述：
 * 输入一个数n，意义见题面。（2 <= n <= 60）
 * <p>
 * 输出描述：
 * 输出答案。
 */
public class LineCutRope {
    /**
     * 第一种方法：只要余1 一定可以和3凑成4也就是两个2
     * 余0的话除了3意外，其余直接取次方即可
     * 余2的话除了2意外，其余的直接3个数次方*2即可
     * <p>
     * 最原始的方法虽然能够解题但是过于麻烦
     */
    public int cutRope(int target) {
        if (target <= 1) return 0;
        // 现在要获取的是最大的乘积
        int m = target % 3;
        int n = target / 3;
        int res = 1;
        if (m == 0) {
            // 表整除
            if (n == 1) {
                res = (int) Math.pow(3, n - 1) * (int) Math.pow(2, n);
            } else {
                res = (int) Math.pow(3, n);
            }
        } else if (m == 1) {
            res = (int) Math.pow(3, n - 1) * (int) Math.pow(2, n + 1);
        } else {
            // m==2,表明只有一个2
            if (n == 0) {
                return 1;
            }
            res = (int) Math.pow(3, n) * m;
        }
        return res;
    }

    /**
     * 跟第一种方法类似，只不过将2 3提取出来了
     */
    public int cutRope1(int target) {
        if (target < 1) return 0;
        /*
                m    n
            2   2    0  1*1
            3   0    1  1*2
            4   1    1  2*2
            5   2    1  3*2
            6   0    2  3*3
            7   1    2  3*2*2
            8   2    2  3*3*2
            9   0    3  3*3*3
            10  1    3  3*3*4
            11  2    3  3*3*3*2
            12  0    4  3*3*3*3

         */
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        int m = target % 3;  // 3余下的数
        int n = target / 3;  // 3的个数
        if (m == 0) {
            return (int) Math.pow(3, n);
        } else if (m == 1) {
            return (int) Math.pow(3, n - 1) * 4;
        } else {
            return (int) Math.pow(3, n) * 2;
        }
    }

    /**
     * 采用动态规划结合贪心算法
     * 就是在我这一段之内的最大值，所以限定dp[2]=2 dp[3]=3因为这种区间不减才是最大的
     */
    public int cutRope2(int target) {
        if (target < 1) return 0;
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        int[] dp = new int[target + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        int max = 0;
        // 贪心算法
        for (int i = 4; i <= target; i++) {
            // 最大的肯定在前半部分和后半部分之间
            for (int j = 1; j <= i / 2; j++) {
                int temp = dp[j] * dp[i - j];
                max = Math.max(max, temp);
            }
            dp[i] = max;
        }
        return dp[target];
    }

    public static void main(String[] args) {
        LineCutRope test = new LineCutRope();
        int i = test.cutRope2(8);
        System.out.println(i);
    }
}
