package demo2;

/*
    synchronize 关键字
 */
public class Test2 {
    public static void main(String[] args) {
        Class1 class1 = new Class1();
        Class1 class12 = new Class1();
        Thread2 thread2 = new Thread2(class1);
        Thread3 thread3 = new Thread3(class1);
        //Thread3 thread3 = new Thread3(class12);

        thread2.start();
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread3.start();
    }
}

class Class1{
    public synchronized void test1(){
        try {
            Thread.sleep(4000); //sleep 不会释放锁
            //wait(4000);  wait会释放锁.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test1");
    }
    public synchronized void test2(){
        System.out.println("test2");
    }
}

class Thread2 extends Thread{
    private Class1 class1;
    public Thread2(Class1 class1){
        this.class1 = class1;
    }
    @Override
    public void run() {
        class1.test1();
    }
}
class Thread3 extends Thread{
    private Class1 class1;
    public Thread3(Class1 class1){
        this.class1 = class1;
    }
    @Override
    public void run() {
        class1.test2();
    }
}