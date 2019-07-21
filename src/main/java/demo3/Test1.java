package demo3;

/*
    synchronize 关键字修饰代码块
 */
public class Test1 {

    private Object object1 = new Object();

    //这个同步块只有一个monitorexit
    public void method2(){
        synchronized (object1){
            System.out.println("method1");
            throw new RuntimeException();
        }
    }

    //这个同步块只有两个monitorexit
    public void method1(){
        synchronized (object1){
            System.out.println("method1");
        }
    }
}
