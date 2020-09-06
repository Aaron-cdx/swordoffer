package com.duanxi.interview.HuaWei.one;

import java.util.*;

/**
 * @author caoduanxi
 * @Date 2020/8/12 18:59
 * 判断找零，即当前是没有任何的，当前找零的可能性只发生在
 * 之前有顾客付了5元，顾客只会给5 10 20的钱
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Integer> map = new HashMap<>();
        int index = 1;
        boolean flag = true;
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            // 如果不在范围之内，直接返回false,index
            if (n != 5 && n != 10 && n != 20) {
                flag = false;
                System.out.print(false);
                System.out.print("," + index);
                break;
            }
            if (n == 5) {
                int num5 = map.getOrDefault(5, 0);
                map.put(5, num5 + 1);
                index++;
            } else if (n == 10) {
                if (!map.containsKey(5)) {
                    flag = false;
                    System.out.print(false);
                    System.out.println("," + index);
                    break;
                } else {
                    int num5 = map.getOrDefault(5, 0);
                    num5 -= 1;
                    if (num5 == 0) {
                        map.remove(5);
                    } else {
                        map.put(5, num5);
                    }
                    map.put(10, map.getOrDefault(10, 0) + 1);
                    index++;
                }
            }
            // 如果是20的话，此时如果前面可以保持3个5或者1个10和1个5
            // 则表示当前是成功的
            else {
                // 先用10
                int num10 = map.getOrDefault(10, 0);
                if (num10 > 0) {
                    int num5 = map.getOrDefault(5, 0);
                    if (num5 > 0) {
                        num10 -= 1;
                        num5 -= 1;
                        if (num5 > 0) {
                            map.put(5, num5);
                        } else {
                            map.remove(5);
                        }
                        if (num10 > 0) {
                            map.put(10, num10);
                        } else {
                            map.remove(10);
                        }
                        map.put(20, map.getOrDefault(20, 0) + 1);
                    } else {
                        flag = false;
                        System.out.print(false);
                        System.out.println("," + index);
                        break;
                    }
                } else {
                    int num5 = map.getOrDefault(5, 0);
                    if (num5 < 3) {
                        flag = false;
                        System.out.print(false);
                        System.out.println("," + index);
                        break;
                    } else {
                        num5 -= 3;
                        if (num5 > 0) {
                            map.put(5, num5);
                        } else {
                            map.remove(5);
                        }
                        map.put(20, map.getOrDefault(20, 0) + 1);
                    }
                }
                index++;
            }
        }
        if (flag) {
            System.out.print(true);
            System.out.print("," + (index - 1));
        }
    }

}
