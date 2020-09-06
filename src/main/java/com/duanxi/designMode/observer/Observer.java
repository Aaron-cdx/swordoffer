package com.duanxi.designMode.observer;

/**
 * @author caoduanxi
 * @Date 2020/8/31 16:25
 */
public class Observer {
    private int uid;

    public Observer(int uid) {
        this.uid = uid;
    }

    public void update(String oldMsg, String modifyMsg) {
        System.out.println(this.uid + "号注册者您好，oldMsg: " + oldMsg + ", 已更新为modifyMsg: " + modifyMsg);
    }

    public static void main(String[] args) {
        Subject subject = new Subject();
        subject.register(new Observer(1));
        subject.register(new Observer(2));
        subject.register(new Observer(3));
        subject.setMsg("哈哈哈哈");
    }
}
