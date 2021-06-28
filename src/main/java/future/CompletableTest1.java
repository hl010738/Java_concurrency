package future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableTest1 {
    public static void main(String[] args) throws InterruptedException {

//        String result = CompletableFuture.supplyAsync(() -> "aaa")
//                .thenApplyAsync(value -> value + " bbb") // 都返回结果
//                .join(); // 合并返回的结果
//
//        System.out.println(result);
//
//        System.out.println("----------------");
//
//        // 处理返回的结果
//        // 最后不返回结果
//        CompletableFuture.supplyAsync(() -> "111").thenAccept(a -> System.out.println(a + "222"));
//
//        System.out.println("---------");
//
//        // 2个任务都是异步执行
//        // 合并结果
//        String result2 = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "!!!!!!!!!!";
//        }).thenCombine(CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            return "@@@@@@@@@";
//        }), (s1, s2) -> s1 + " " + s2).join();
//
//        System.out.println(result2);
//
//        System.out.println("-------------------");

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task finished");
        });

        completableFuture.whenComplete((t, action) -> System.out.println("执行完成"));

        System.out.println("主线程执行完毕");

        TimeUnit.SECONDS.sleep(7);
    }
}
