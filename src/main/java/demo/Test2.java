package demo;

/*
    程序目的

    1. 存在一个对象，该对象有一个int类型的成员变量counter，该成员变量的初始值为0
    2. 创建2个线程，其中一个线程对该对象的成员变量增1，另一个线程则减1
    3. 输出该对象成员变量每次变化后的值
    4. 最终输出的结果为：101010101010...
 */
public class Test2 {
    public static void main(String[] args) {
        Object2 obj = new Object2();

        DecreaseThread decreaseThread = new DecreaseThread(obj);
        DecreaseThread decreaseThread1 = new DecreaseThread(obj);
        IncreaseThread increaseThread = new IncreaseThread(obj);
        IncreaseThread increaseThread1 = new IncreaseThread(obj);

        decreaseThread.start();
        decreaseThread1.start();
        increaseThread.start();
        increaseThread1.start();
    }
}

class DecreaseThread extends Thread{
    private Object2 obj;
    public DecreaseThread(Object2 obj){
        this.obj = obj;
    }
    @Override
    public void run() {
        for (int i = 0; i < 30; i++){
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            obj.decrease();
        }
    }
}

class IncreaseThread extends Thread{
    private Object2 obj;
    public IncreaseThread(Object2 obj){
        this.obj = obj;
    }
    @Override
    public void run() {
        for (int i = 0; i < 30; i++ ){
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            obj.increase();
        }
    }
}

class Object2{
    private int counter;
    public synchronized void increase() {
        //if (counter != 0){ if 只能适应2个线程的情况
        while (counter != 0) { // while 适应多个线程的情况
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter++;
        System.out.print(counter);
        notify();
    }
    public synchronized void decrease(){
        //if (counter == 0){ if 只能适应2个线程的情况
        while (counter == 0){ // while 适应多个线程的情况
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter--;
        System.out.print(counter);
        notify();
    }
}
