package com.duanxi.books.charpeterTwo;

/**
 * @author caoduanxi
 * @2019/12/2 21:30
 * 单例模式的实现
 * 单例模式的实现有懒汉式、饿汉式、静态私有内部类
 * 懒汉式第一种没有加锁，不安全，加锁之后，由于第一种加锁容易使得第二个到达的线程重复加锁，所以使用if语句重复判断
 * 饿汉式比较安全，一开始就实例化，用的时候直接返回即可
 * 最后是使用静态私有内部类，在类内部构建私有的内部类，私有的内部类中实例化单例对象，此种方式实现了按需创建
 * 适合高并发，效率高，比较常用
 */
public class SingleTonImplements {
    // 只适合单线程
    static class SingleTon1 {
        // 私有的构造方法
        private static SingleTon1 singleTon1;

        // 私有的构造方法
        private SingleTon1() {
        }

        // 非线程安全的
        public static SingleTon1 getSingleTon1Instance() {
            if (singleTon1 == null) {
                singleTon1 = new SingleTon1();
            }
            return singleTon1;
        }
    }

    // 多线程，加锁 懒汉式
    static class SingleTon2 {
        // 加volatile关键字保证了内存的可见性。
        // 所有线程都会去主存中获取数据，而不是在缓存中获取，保证了数据的更新能实时对任何线程可见
        private volatile static SingleTon2 singleTon2;

        private SingleTon2() {

        }

        public static SingleTon2 getSingleTon2Instance() {
            // 利用synchronized关键字，保证线程安全,加锁是一个很耗时的操作
            // 第一个版本，为了防止重复加锁
            /*synchronized (SingleTon2.class){
                if(singleTon2 == null){
                    singleTon2 = new SingleTon2();
                }
                return singleTon2;
            }*/
            // 第二个版本,双重判断，防止第一个线程进入创建对象的时候，创建完只有释放锁
            // 第二个线程又进去，二次加锁
            // 代码比较复杂容易出错
            if (singleTon2 == null) {
                synchronized (SingleTon2.class) {
                    if (singleTon2 == null) {
                        singleTon2 = new SingleTon2();
                    }
                }
            }
            return singleTon2;
        }
    }

    // 强烈推荐的解法：静态构造函数法
    // 饿汉式：支持并发，因为对象只会初始化一次
    static class SingleTon3 {
        private SingleTon3() {

        }

        // 构建一个静态的对象
        private static SingleTon3 singleTon3 = new SingleTon3();

        public static SingleTon3 getSingleTon3Instance() {
            return singleTon3;
        }
    }

    // 实现按需创建实例
    // 静态私有内部类(常用)，支持高并发，效率高
    static class SingleTon4{
        private SingleTon4(){

        }
        public static SingleTon4 getSingleTon4Instance(){
            return Nested.singleTon4;
        }
        // 使用静态私有内部类，来创建
        static class Nested{
            private static SingleTon4 singleTon4 = new SingleTon4();
        }
    }



    public static void main(String[] args) {
        SingleTon3 a = SingleTon3.getSingleTon3Instance();
        SingleTon3 b = SingleTon3.getSingleTon3Instance();
        System.out.println(a);
        System.out.println(b);
        System.out.println(a==b);
        SingleTon4 c = SingleTon4.getSingleTon4Instance();
        SingleTon4 d = SingleTon4.getSingleTon4Instance();
        System.out.println(c);
        System.out.println(d);
    }

}
