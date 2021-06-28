package future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTest1 {
    public static void main(String[] args) throws Exception {

        Callable<Integer> callable = () -> {
            System.out.println("===============");

            Thread.sleep(5000);
            int random = new Random().nextInt(300);

            System.out.println("++++++++++++++");

            return random;
        };

        FutureTask<Integer> futureTask = new FutureTask(callable);

        new Thread(futureTask).start();

        System.out.println("----------------");

        Thread.sleep(2000);
        // 超时后会抛出异常，但不会影响子线程的执行
        System.out.println(futureTask.get(1, TimeUnit.MILLISECONDS));
    }
}
