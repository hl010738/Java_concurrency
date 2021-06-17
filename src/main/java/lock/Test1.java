package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test1 {

    private final Lock lock = new ReentrantLock(); // 可重入锁

    public static void main(String[] args) {
        Test1 test1 = new Test1();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++){
                test1.method1();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++){
                test1.method2();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }

    public void method2() {
//        try {
//            lock.lock();
//            System.out.println("method2 invoked");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }

        boolean result = false;

        try {
            lock.tryLock(800, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (result){
            System.out.println("get the lock");
        } else {
            System.out.println("can not get the lock");
        }
    }

    public void method1() {
        try {
            lock.lock();
            System.out.println("method1 invoked");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 当这行代码被注释掉后
            // method2 无法获取到锁
            // 并且线程会一直等待
            // 导致程序无法执行
            //lock.unlock();
        }
    }
}
