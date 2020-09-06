package com.duanxi.designMode.stragety;

/**
 * @author caoduanxi
 * @Date 2020/8/31 16:15
 */
public class Dog implements Strategy {

    @Override
    public void sayHello(String msg) {
        // "I'm dog, wa wa wa ~~~"
        System.out.println(msg);
    }
}
