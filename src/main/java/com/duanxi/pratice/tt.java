package com.duanxi.pratice;

/**
 * @author caoduanxi
 * @Date 2020/6/29 22:52
 */
public class tt implements TestInterface {
    @Override
    public void test() {
        System.out.println("测试接口特性");
    }

    public static void main(String[] args) {
        tt tt = new tt();
        TestInterface.test2();
    }
}

