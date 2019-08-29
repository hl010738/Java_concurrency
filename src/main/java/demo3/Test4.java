package demo3;

/*
 * 编译器对于锁的优化措施
 * 锁消除：JIT编译器可以在动态编译同步代码时，使用一种叫做逃逸分析的技术. 通过该项技术判别程序中所使用的锁对象是否只被一个线程使用，而没有散布到其他线程当中；如果情况是这样的话，那么JIT编译器在编译这个同步代时就不会生成synchronize关键字锁标识的锁的申请于释放的机器码，从而消除锁的使用流程.
 */
public class Test4 {
    //private Object object = new Object();

    public void method(){
        Object object = new Object();
        synchronized (object){
            System.out.println("hello world");
        }
    }
}
