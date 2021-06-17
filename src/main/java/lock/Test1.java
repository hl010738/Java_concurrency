package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*

	* Lock 与 synchronized 关键字在锁的处理上的重要差别

		* 锁的获取方式

			* Lock 是通过由程序代码的方式由开发者手工获取
			* synchronized 是由JVM 自动处理，无需手工干预
		* 具体实现方式

			* Lock 是由Java 代码方式实现
			* synchronized 是通过JVM底层实现
		* 锁的释放

			* lock 必须通过unlock方法在finally块中手工释放
			* synchronized 由JVM 自动释放
		* 锁的具体类型

			* lock 提供了多种类型的锁，例如公平锁、非公平锁，也提供可重入锁.
			* synchronized 提供了可重入锁

 */
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
