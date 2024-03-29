package executor;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class CompletionServiceTest {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = new ThreadPoolExecutor(4, 10, 10, TimeUnit.SECONDS
        , new LinkedBlockingQueue<>(20)
        , new ThreadPoolExecutor.AbortPolicy());

        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

        IntStream.range(0, 10).forEach(a -> {
            completionService.submit(() -> {
                TimeUnit.SECONDS.sleep((long) Math.random());

                System.out.println(Thread.currentThread().getName());

                return a * a;
            });
        });

        for (int i = 0; i <= 10; i++) {
            int result = completionService.take().get();
            System.out.println(result);
        }

        executorService.shutdownNow();
    }
}
