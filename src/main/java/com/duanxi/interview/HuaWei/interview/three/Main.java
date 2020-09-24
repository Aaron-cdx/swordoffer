package com.duanxi.interview.HuaWei.interview.three;

/**
 * @Author: caoduanxi
 * @Date: 2020/9/16
 */
public class Main {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        System.out.println("Hello World!");
        Main main = new Main();
        int[] res = main.getTargetIndex(new int[]{8,8,8,8,8,8},8);
        for(int i:res){
            System.out.print(i + " ");
        }
    }

    /** 返回首位 **/
    public int[] getTargetIndex(int[] nums, int target){
        int[] res = new int[]{-1,-1};
        if(nums == null || nums.length == 0) return res;

        // 先向左逼近
        int l = 0;
        int r = nums.length - 1;
        while( l < r){
            int mid = (l+r)>>1;
            if(nums[mid] >= target){
                r = mid;
            }else{
                l = mid+1;
            }
        }
        if(nums[l] != target) return res;
        res[0] = l;
        r = nums.length - 1;
        while(l < r){
            int mid = (l+r) >> 1;
            if(nums[mid] <= target){
                l = mid + 1;
            }else{
                r = mid;
            }
        }
        res[1] = l-1;
        return res;
    }
}
