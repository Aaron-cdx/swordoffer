package com.duanxi.designMode.stragety;

/**
 * @author caoduanxi
 * @Date 2020/8/31 16:15
 */
public class People implements Strategy {
    @Override
    public void sayHello(String msg) {
        // "I'm people, hello!"
        System.out.println(msg);
    }
}
