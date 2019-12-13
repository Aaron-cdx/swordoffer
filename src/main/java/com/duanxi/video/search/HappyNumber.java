package com.duanxi.video.search;

import java.util.HashSet;
import java.util.Set;

/**
 * @author caoduanxi
 * @2019/12/13 19:05
 * leetcode 202 快乐数
 * <p>
 * 编写一个算法来判断一个数是不是“快乐数”。
 * <p>
 * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，
 * 也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
 * <p>
 * 示例: 
 * <p>
 * 输入: 19
 * 输出: true
 * 解释:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/happy-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HappyNumber {
    // 这是找到数学规律之后的解法，利用递归，如果没有找到数学规律的话，使用下面的解法
    public static boolean isHappy1(int n) {
        int sum = 0;
        while (n != 0) {
            sum += Math.pow(n % 10, 2);
            n /= 10;
        }
        if (sum == 1) {
            return true;
        } else {
            if (sum == 4 || sum == 16 || sum == 37 || sum == 58
                    || sum == 89 || sum == 145 || sum == 42 || sum == 20) {
                return false;
            }
            return isHappy1(sum);
        }
    }

    // 只要出现了重复就证明是出现了循环，则一定不是快乐数
    // 使用集合时间空间复杂度比较高
    public static boolean isHappy2(int n) {
        Set<Integer> set = new HashSet<>();
        int m = 0;
        while (n != 0) {
            m += Math.pow(n % 10, 2);
            n /= 10;

            if (m == 1 && n == 0) {
                return true;
            }
            // 如果set中没有，且n!=0,此时加入，如果set中有，且n==0，就直接返回false
            if (set.contains(m) && n == 0) {
                return false;
                // 如果n==0的话，此时又不包含m，此时就添加，然后将m作为下一个的运算数
            } else if (!set.contains(m) && n == 0) {
                set.add(m);
                n = m;
                m = 0;
            }
        }
        return false;
    }

    // 使用快慢指针
    // 对啊，这样总会遇到
    public static boolean isHappy(int n) {
        int fast = n;
        int slow = n;
        // 如果不是快乐数，总会再次相遇的，因为非快乐数会成为一个循环，总会遇到的，一旦遇到如果此时slow不为1
        // 就可以表明不是快乐数
        do {
            slow = getNum(slow);
            fast = getNum(fast);
            fast = getNum(fast);
        } while (slow != fast);
        return slow == 1;
    }

    private static int getNum(int n) {
        int sum = 0;
        while (n != 0) {
            int s = n % 10;
            sum += s * s;
            n /= 10;
        }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }
}
