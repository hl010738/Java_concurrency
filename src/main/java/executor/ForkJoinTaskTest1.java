package executor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTaskTest1 {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        Task1 task1 = new Task1(1, 100);

        int result = forkJoinPool.invoke(task1);

        System.out.println(result);

        forkJoinPool.shutdown();
    }
}

class Task1 extends RecursiveTask<Integer> {
     private int limit = 4;

     private int firstIndex;

     private int lastIndex;

    public Task1(int firstIndex, int lastIndex) {
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
    }

    @Override
    protected Integer compute() {

        int result = 0;

        int gap = lastIndex - firstIndex;

        boolean flag = gap < limit;

        if (flag) {
            System.out.println(Thread.currentThread().getName());
            for (int i = firstIndex; i <= lastIndex; i++) {
                result += i;
            }
        } else {
            int middleIndex = (firstIndex + lastIndex) / 2;

            Task1 leftTask = new Task1(firstIndex, middleIndex);
            Task1 rightTask = new Task1(middleIndex + 1, lastIndex);

            invokeAll(leftTask, rightTask);

            int leftTaskResult = leftTask.join();
            int rightTaskResult = rightTask.join();

            result = leftTaskResult + rightTaskResult;
        }

        return result;
    }
}
