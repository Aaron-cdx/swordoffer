package com.duanxi.pratice;

/**
 * @author caoduanxi
 * @Date 2020/6/29 22:49
 * 接口中的方法都是抽象的
 */
public interface TestInterface {
    public abstract void test();

    public static void test2() {
        System.out.println("可以有方法体");
    }
}
