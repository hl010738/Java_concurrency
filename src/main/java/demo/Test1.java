package demo;

/*
    在调用wait方法时，线程必须持有被调用对象的锁，当调用wait方法后，线程会释放掉该对象的锁.
    在调用Thread类的sleep方法时，线程并不会释放对象的锁.
 */

public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();

        synchronized (obj){
            obj.wait();
        }
    }
}
