package com.duanxi.designMode.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caoduanxi
 * @Date 2020/8/31 16:26
 */
public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private String msg = "呵呵呵";

    private String getMsg() {
        return this.msg;
    }

    // 数据变化，如何变化，通知观察者们
    public void setMsg(String modifyMsg) {
        String oldMsg = getMsg();
        notifyObservers(oldMsg, modifyMsg);
    }

    public void register(Observer observer) {
        this.observers.add(observer);
    }

    private void notifyObservers(String oldMsg, String modifyMsg) {
        for (Observer observer : observers) {
            observer.update(oldMsg, modifyMsg);
        }
    }
}
