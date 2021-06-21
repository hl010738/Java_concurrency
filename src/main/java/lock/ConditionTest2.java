package lock;


import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/*

	* 传统上，通过synchronized关键字 + wait + notify/notifyAll 来实现多个线程之间的协调和通讯，整个过程都是由JVM来实现的，开发者无法控制也无法了解提层实现细节
	* 从JDK 5 开始，并发包童工了Lock， Condition（await与signal/signalAll）来实现多个线程之间的协调与通信，整个过程都是由开发这来控制的，而且相比于传统方式，更加灵活.
	* Condition 在创建时就与一个Lock 绑定, 当调用 condition.await 方法，会释放掉condition 锁关联的Lock 锁持有的锁
	* Thread.sleep 与 await 方法（或者是Object的wait方法）的本质区别：sleep 方法本质上不会释放锁，而await会释放锁，并且的signal后，还需要重新获得锁才能继续执行（该行为与Object的wait方法完全一致）

 */
public class ConditionTest2 {


}

class BoundedContainer {

    private String[] elements = new String[10];

    private Lock lock = new ReentrantLock();

    private Condition notEmptyCondition = lock.newCondition();

    private Condition notFullCondition = lock.newCondition();

    private int elementCount; // elements数组中已有的元素

    private int putIndex;

    private int takeIndex;

    public static void main(String[] args) {

        BoundedContainer boundedContainer = new BoundedContainer();

        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            try {
                boundedContainer.put("aaa");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());

        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            try {
                boundedContainer.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());
    }

    public String take() throws InterruptedException {

        this.lock.lock();

        try {
            while (0 == this.elementCount){
                notEmptyCondition.await();
            }

            String element = elements[takeIndex];

            elements[takeIndex] = null;

            if (++takeIndex == this.elements.length) {
                takeIndex = 0;
            }

            --elementCount;

            System.out.println("take method: " + Arrays.toString(elements));

            notFullCondition.signal();

            return element;
        } finally {
            this .lock.unlock();
        }
    }

    public void put(String element) throws InterruptedException {

        this.lock.lock();

        try {
            while (this.elementCount == this.elements.length){
                notFullCondition.await();
            }

            elements[putIndex] = element;

            if (++putIndex == this.elements.length){
                putIndex = 0;
            }

            ++elementCount;

            System.out.println("put method: " + Arrays.toString(elements));

            notEmptyCondition.signal();

        } finally {
            this.lock.unlock();
        }
    }
}
