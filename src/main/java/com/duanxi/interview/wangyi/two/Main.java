package com.duanxi.interview.wangyi.two;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author caoduanxi
 * @Date 2020/8/8 15:23
 * 即给定特定个元素，获取当前特定元素的缺少元素填充数组，然后再输出即可
 * 5 3
 * 2 1 5 => 2 1 3 4 5
 * <p>
 * 5 2
 * 4 2 => 1 3 4 2 5
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        // 输入数据的个数
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int m_index = m;
        int[] nums = new int[m];
        int i = 0;
        while (m > 0) {
            nums[i++] = scanner.nextInt();
            m--;
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        list = main.getNums(list, n, m_index);
        for (Integer num : list) {
            System.out.print(num + " ");
        }

    }

    public List<Integer> getNums(List<Integer> list, int n,int m) {
        int cnt = 1;
        int[] nums = new int[n-m];
        int n_cnt = 0;
        while (cnt <= n) {
            if (!list.contains(cnt)) {
                nums[n_cnt++] = cnt;
            }
            cnt++;
        }
        // 此时numList元素是有序的[3,4]
        int c_index = 0;
        int num_index = 0;
        while (num_index < nums.length) {
            int index = -1;
            for (int i = c_index; i < list.size(); i++) {
                if (nums[num_index] < list.get(i)) {
                    index = i;
                    c_index = i;
                    list.add(i, nums[num_index++]);
                    break;
                }
            }
            if (index == -1) {
                list.add(nums[num_index++]);
                c_index = list.size()-1;
            }
        }
        return list;
    }

    /**
     * 时间复杂度过高
     */
    public List<Integer> getNums2(List<Integer> list, int n, int m) {
        int cnt = 1;
        List<Integer> numList = new ArrayList<>();
        while (cnt <= n) {
            if (!list.contains(cnt)) {
                numList.add(cnt);
            }
            cnt++;
        }
        // 此时numList元素是有序的[3,4]
        int c_index = 0;
        while (numList.size() != 0) {
            int index = -1;
            for (int i = c_index; i < list.size(); i++) {
                if (numList.get(0) < list.get(i)) {
                    index = i;
                    c_index = i;
                    list.add(index, numList.get(0));
                    numList.remove(0);
                    break;
                }
            }
            if (index == -1) {
                list.add(numList.get(0));
                c_index = list.size()-1;
                numList.remove(0);
            }
        }

        return list;
    }

    public List<Integer> getNums1(List<Integer> list, int n) {
        int cnt = 1;
        List<Integer> numList = new ArrayList<>();
        while (cnt <= n) {
            if (!list.contains(cnt)) {
                int index = -1;
                for (int i = 0; i < list.size(); i++) {
                    if (cnt < list.get(i)) {
                        index = i;
                        list.add(index, cnt);
                        break;
                    }
                }
                if (index == -1) {
                    list.add(cnt);
                }
            }
            cnt++;
        }
//        // 此时numList元素是有序的[3,4]
//        while (numList.size() != 0) {
//            int index = -1;
//            for (int i = 0; i < list.size(); i++) {
//                if (numList.get(0) < list.get(i)) {
//                    index = i;
//                    list.add(index, numList.get(0));
//                    numList.remove(0);
//                    break;
//                }
//            }
//            if (index == -1) {
//                list.add(numList.get(0));
//                numList.remove(0);
//            }
//        }
        return list;
    }
}
