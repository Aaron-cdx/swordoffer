package com.duanxi.newcode;

import java.util.Arrays;

/**
 * @author caoduanxi
 * @Date 2020/2/6 14:10
 * 牛客网：扑克牌顺子
 * <p>
 * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,
 * 2个小王(一副牌原本是54张^_^)...他随机从中抽出了5张牌,
 * 想测测自己的手气,看看能不能抽到顺子,如果抽到的话,
 * 他决定去买体育彩票,嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,
 * “Oh My God!”不是顺子.....LL不高兴了,他想了想,
 * 决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
 * 上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。
 * LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何，
 * 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0
 */
public class IsContinuous {
    /**
     * 题目的意思是想让你找出numbers中的数字是否成凑成顺子
     * <p>
     * 先排序，找出0也就是大小王的个数
     * 在运算的过程中，只要发现对子出现，就直接返回false
     * 如果没有对子，也没有0，就计算数字之间的间隔
     * 最后看看间隔中的数字是否可以使用大小王来代替
     * 最后解决！
     */
    public boolean isContinuous(int[] numbers) {
        // 判定字符串能够组成顺子，false or true
        // 随机从中抽5张牌，能组成顺子返回true，否则返回false
        // numbers
        Arrays.sort(numbers); // 先排序
        int numOfZero = 0;
        int numOfInterval = 0;
        int n = numbers.length;
        if (n == 0) return false;
        for (int i = 0; i < n - 1; i++) {
            if (numbers[i] == 0) {
                numOfZero++;
                continue;
            }
            // 出现对子，直接返回false
            if (numbers[i] == numbers[i + 1]) {
                return false;
            }
            // 如果不是0，也不是对子，此时计算间隔
            numOfInterval += numbers[i + 1] - numbers[i] - 1;
        }
        // 这里要注意等于的情况，正好抵消
        return numOfInterval <= numOfZero;
    }
}
