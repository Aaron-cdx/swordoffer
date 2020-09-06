package com.duanxi.interview;

/**
 * @author caoduanxi
 * @Date 2020/9/3 16:13
 */
public class DeadLock {
    public static final Object object1 = new Object();
    public static final Object object2 = new Object();

    // A获取到自己的锁，然后又去请求B的锁
    // B
    public static void main(String[] args) {
        Thread t1 = new Thread(new Lock1(), "Thread-A");
        Thread t2 = new Thread(new Lock2(), "Thread-B");
        t1.start();
        t2.start();
    }

}

class Lock1 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " get Lock1");
        while (true) {
            synchronized (DeadLock.object1) {
                System.out.println(Thread.currentThread().getName() + " get object1");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (DeadLock.object2) {
                    System.out.println(Thread.currentThread().getName() + " get object2");
                }
            }
        }
    }
}

class Lock2 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " get Lock2");
        while (true) {
            synchronized (DeadLock.object2) {
                System.out.println(Thread.currentThread().getName() + " get object2");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (DeadLock.object1) {
                    System.out.println(Thread.currentThread().getName() + " get object1");
                }
            }
        }
    }
}
