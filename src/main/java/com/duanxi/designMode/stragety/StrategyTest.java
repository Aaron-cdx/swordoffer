package com.duanxi.designMode.stragety;

/**
 * @author caoduanxi
 * @Date 2020/8/31 16:17
 * 策略模式的实现，通过一个执行类来装配所需要执行的当前的类的具体执行信息
 *
 */
public class StrategyTest {
    public static void main(String[] args) {
        HelloExecute helloExecute = new HelloExecute(new Dog());
        helloExecute.executeHello(" hello, I'm people!");
    }
}
