package com.duanxi.interview.Tencent.one;


import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/8/31 20:44
 * 面试官
 * 100,4,200,1,3,2
 * 面试官
 * 1,2,3,4
 * <p>
 * 我先写吧  切电话  电脑就下线了
 * 好的
 */
public class SingleTon {
    public static void main(String[] args) {
        SingleTon singleTon = new SingleTon();
        int longSubsenquence = singleTon.getLongSubsenquence(new int[]{100, 4, 200, 1, 3, 2});

    }
    // 大概的思路出来了

    public int getLongSubsenquence(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i]-1)) {
                int left = map.get(arr[i] - 1) + 1;

                map.put(i, left);
                map.put(arr[i]-1,left);
            }
            if(map.containsKey(arr[i]+1)){
                int right = map.get(arr[i]+1)+1;
                map.put(i,right);
                map.put(arr[i]+1,right);
            }
            if(!map.containsKey(i)){
                map.put(i,1);
            }
        }
        int count = Integer.MIN_VALUE;
        // 找出最大的count的最大值。然后构造当前的数组


        return 0;
    }


    private volatile SingleTon singleTon;

    private SingleTon() {
    }

    public SingleTon getInstance() {
        if (singleTon == null) {
            synchronized (SingleTon.class) {
                if (singleTon == null) {
                    singleTon = new SingleTon();
                }
            }
        }
        return singleTon;
    }
}
