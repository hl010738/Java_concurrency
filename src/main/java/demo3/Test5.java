package demo3;

/*
    锁粗化：
    样做的好处在于线程在执行这些代码时，就无需频繁申请和释放锁，从而达到申请和释放锁一次，就可以执行完全部的synchronize代码，从而提升性能.

 */
public class Test5 {
    private Object object = new Object();

    public void method(){
        synchronized (object){
            System.out.println("11111");
        }
        synchronized (object){
            System.out.println("22222");
        }
        synchronized (object){
            System.out.println("33333");
        }
    }
}
