package com.duanxi.interview.Bilibili.three;


/**
 * 标准的背包问题
 * 即1 4 16 64的硬币填充1024-N的背包，如何让其硬币数量最小。
 * 先用64填充，不够的话用16填充，然后用4填充，最后用1填充
 */
public class Solution {
    /**
     * 
     * @param N int整型 
     * @return int整型
     */
    public int GetCoinCount (int N) {
        // write code here
        N = 1024 - N;
        int cnt= 0;
        if(N >= 64){
            int n = N/64;
            N = N - 64*n;
            cnt += n;
        }
        if(N >= 16){
            int n = N/16;
            N = N - 16*n;
            cnt += n;
        }
        if(N >= 4){
            int n = N/4;
            N = N - 4*n;
            cnt += n;
        }
        if(N >= 1){
            int n = N;
            N = 0;
            cnt += n;
        }
        return cnt;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.GetCoinCount(200));
    }
}