package com.duanxi.byteDance;

/**
 * @author caoduanxi
 * @Date 2020/2/27 22:48
 * 字节跳动：数组中出现次数超过一般的数字
 * <p>
 * 找出数组中出现次数超过一般的数字，不存在输出0
 */
public class MoreThanHalfNumber {
    /**
     * 思路就是超过一般的数一定会在最后出来，只要跟与自己不一样的抵消即可
     * 超过一般的数最后一定会数量大于一半。所以只需要前后抵消，计算最后的num次数即可
     */
    public int findNumber(int[] nums) {
        if (nums == null) return 0;
        int n = nums.length;
        int count = 1;
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(num == nums[i]){
                count++;
            }else{
                count--;
            }
            if(count == 0){
                count = 1;
                num = nums[i];
            }
        }
        count = 0;
        for (int i : nums) {
            if(i == num){
                count++;
            }
        }
        return count > (n/2) ? num : 0;
    }

    public static void main(String[] args) {
        MoreThanHalfNumber test = new MoreThanHalfNumber();
        System.out.println(test.findNumber(new int[]{1,2,3,2,2,2,5,4,2}));
    }
}
