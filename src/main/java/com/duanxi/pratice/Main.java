package com.duanxi.pratice;

import java.util.Scanner;

/**
 * @author caoduanxi
 * @Date 2020/6/21 22:58
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int num = sc.nextInt();
            int result = 0;

            int[][] arr = new int[num + 1][];
            for (int i = 3; i <= num; i++) {
                int columnNum = (int) Math.floor((i - 1) / 2d); // 假如是7，这里column就是3
                arr[i] = new int[columnNum];// 横坐标表示当前分析的数，这里表示纵坐标为0 1 2
                for (int j = 0; j < columnNum; j++) {
                    arr[i][j] = 1;// 首先
                    int num2 = j + 1;
                    int num1 = i - num2;
                    if (num1 > 2 * num2) {//只有这种情况才分解
                        if (num1 % 2 == 0) {//num1是偶数，计算分解情况时+1
                            arr[i][j]++;// 偶数的时候有一种就是2+2，即偶数的时候需要多考虑一重
                        }
                        //计算数1的分解情况
                        for (int k = j; k < arr[num1].length; k++) {
                            arr[i][j] += arr[num1][k];
                        }

                    }
                }

            }
//            输出整个二维数组的情况
            for(int i = 3; i <= num; i++){
                for(int j = 0; j < arr[i].length;j++){
                    System.out.println("arr["+i+"]["+j+"] is: "+arr[i][j]);
                }
            }
            if (num == 1 || num == 2) result = 0;
            else {
                for (int i = 0; i < arr[num].length; i++) {
                    result += arr[num][i];
                }
            }
            System.out.println(result);
        }
    }
}
