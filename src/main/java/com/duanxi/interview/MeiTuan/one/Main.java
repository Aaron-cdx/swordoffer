package com.duanxi.interview.MeiTuan.one;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/8/15 16:07
 * 逆序数，1234 => 4321表示逆序数
 * <p>
 * 如果一个数的逆序数是自己的四倍，则构成逆序对
 */
public class Main {
    // map是无序的
    public static void main(String[] args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
//        while(scanner.hasNext()){
            long n = scanner.nextLong();
            if(n < 1){
                System.out.println(0);
                return;
            }
            Map<Long, Long> map = new HashMap<>();
            main.getReversePairs(n, map);
            System.out.println(map.size());
            Set<Long> longs = map.keySet();
            List<Long> list = new ArrayList<>();
            list.addAll(longs);
            Collections.sort(list);
            for (Long aLong : list) {
                System.out.println(aLong + " " + map.get(aLong));
            }
//        }
    }

    public void getReversePairs(long n, Map<Long, Long> map) {
        for (long i = 10; i < n; i++) {
            long reverseNum = getReverseNum(i);
            if (reverseNum % 4 == 0 && reverseNum / 4 == i) {
                map.put(i, reverseNum);
            }
        }
    }

    public long getReverseNum(long num) {
        String s = String.valueOf(num);
        long sum = 0;
        int index = 1;
        for (int i = 0; i < s.length(); i++) {
            if((s.charAt(0)-'0')%2 == 1) break;
            if (s.charAt(i) - '0' == '0') continue;
            sum += (s.charAt(i) - '0') * (long) Math.pow(10, index - 1);
            index++;
        }
        return sum;
    }
}
