package demo4;

import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/*
    CyclicBarrier
 */
public class Test2 {
    public static void main(String[] args) {

        Runnable barrierAction = () -> System.out.println("------------");

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, barrierAction);


        IntStream.range(0, 2).forEach(i -> new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("aaaaaaaa");
                cyclicBarrier.await();
                System.out.println("bbbbbbbbbb");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start());

        IntStream.range(0, 3).forEach(i -> new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("ccccccc");
                cyclicBarrier.await();
                System.out.println("dddddddd");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start());

        IntStream.range(0, 1).forEach(i -> new Thread(() -> {
            try {
                Thread.sleep(10000);
                System.out.println("eeeeee");
                cyclicBarrier.await();
                System.out.println("ffffffffff");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start());
    }
}
