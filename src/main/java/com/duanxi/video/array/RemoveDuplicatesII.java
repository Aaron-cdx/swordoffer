package com.duanxi.video.array;

/**
 * @author caoduanxi
 * @2019/12/2 21:08
 * leetcode 80 删除排序数组中重复元素II 每个元素最多出现2次
 * <p>
 * 给定 nums = [1,1,1,2,2,3],
 * <p>
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveDuplicatesII {
    // 重复的元素最多出现两次
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int b = 0;
        int cnt = 1;
        for (int i = 1; i < nums.length; i++) {
            // 如果两者相等，此时cnt=2,如果再遇到1，此时为3
            if ((cnt < 2)) {
                if (nums[i] == nums[b]) {
                    // 如果数量没到2,且相等，存放，数量+1
                    nums[++b] = nums[i];
                    cnt += 1;
                } else {
                    // 如果不相等，直接存放，数量置为1
                    nums[++b] = nums[i];
                    cnt = 1;
                }
            } else {
                // 如果数量>=2，只有遇到不等的时候才存放，否则一直向前走
                if (nums[i] != nums[b]) {
                    nums[++b] = nums[i];
                    cnt = 1;
                }
            }
        }
        return b + 1;
    }
    // 这种解法更简洁
    // 这种解法可以设置为n个数，就是设置这个数可以出现n次
    public static int removeDuplicates2(int[] nums) {
        if (nums.length == 0) return 0;
        int cnt = 0;
        for (int num : nums) {
            // 如果大于等于2的话只能使用第二种判断了，只能判断自己与自己-2位置的数是否相等，不等才加入
            if(cnt < 2 || num != nums[cnt - 2]){
                nums[cnt] = num;
                cnt += 1;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1,1,1,2,2,3};
        int[] nums = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};
        int index = removeDuplicates2(nums);
        for (int i = 0; i < index; i++) {
            System.out.println(nums[i]);
        }
    }
}
