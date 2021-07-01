package executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Test1 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(() -> {
            IntStream.range(0, 50).forEach(a -> {
                System.out.println(Thread.currentThread().getName());
            });
        });

        executorService.shutdown();
    }
}
