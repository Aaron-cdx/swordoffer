package com.duanxi.interview;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author caoduanxi
 * @Date 2020/2/11 16:02
 */
public class TestLock {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        try {
            System.out.println("测试是否会抛异常");
        }finally {
            lock.unlock();
        }
    }
}
