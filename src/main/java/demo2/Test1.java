package demo2;

/*
    Synchronize 关键字
 */
public class Test1 {
    public static void main(String[] args) {
        Runnable r = new Thread1();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
    }
}

class Thread1 implements Runnable{
    int s;

    @Override
    public void run() {
        s = 0;
        while (true){
            System.out.println("result: " + s++);
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (s == 30){
                break;
            }
        }
    }
}