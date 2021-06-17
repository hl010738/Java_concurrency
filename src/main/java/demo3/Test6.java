package demo3;


/*

	* 死锁

		* 线程1等待线程2互斥持有的资源，而线程2也等待线程1互斥持有的资源，从而导致两个线程都无法继续执行
	* 活锁

		* 线程持续尝试一个总是失败的操作，导致无法继续执行.
	* 饿死

		* 线程一直被调度器延迟访问其依赖执行的资源，也许是先于低优先级而执行高优先级的线程，同时总是有一个高优先级的线程可以执行. 简而言之就是线程优先级太，CPU资源总是被高优先级的线程抢占导致低优先级的线程总是在等待.

 */
public class Test6 {

    private Object lock1 = new Object();

    private Object lock2 = new Object();


    public void method1(){
        synchronized (lock1){
            synchronized (lock2){
                System.out.println("method1 invoked");
            }
        }
    }

    public void method2() {
        synchronized (lock2){
            synchronized (lock1) {
                System.out.println("method2 invoked");
            }
        }
    }

    public static void main(String[] args) {
        Test6 test6 = new Test6();

        Runnable runnable1 = () -> {
            while (true) {
                test6.method1();
                try {
                    Thread.sleep(100);
                } catch (Exception e) {

                }
            }
        };

        Thread thread1 = new Thread(runnable1, "thread1");

        Runnable runnable2 = () -> {
            while (true) {
                test6.method2();
                try {
                    Thread.sleep(250);
                } catch (Exception e) {

                }
            }
        };

        Thread thread2 = new Thread(runnable2, "thread2");

        thread1.start();
        thread2.start();
    }
}
