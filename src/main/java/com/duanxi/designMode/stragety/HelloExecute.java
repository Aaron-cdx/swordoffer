package com.duanxi.designMode.stragety;

/**
 * @author caoduanxi
 * @Date 2020/8/31 16:16
 */
public class HelloExecute {
    private Strategy strategy;

    public HelloExecute(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeHello(String hello) {
        strategy.sayHello(hello);
    }
}
