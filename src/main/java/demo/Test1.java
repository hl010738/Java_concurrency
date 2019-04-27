package demo;

/*
    在调用wait方法时，线程必须持有被调用对象的锁，当调用wait方法后，线程会释放掉该对象的锁.
    在调用Thread类的sleep方法时，线程并不会释放对象的锁.

    关于wait，notify和notifyAll方法的总结：
        1. 当调用wait方法时，首先需要确保调用wait方法的线程已经持有了被调用对象的锁.
        2. 当调用wait方法后，该线程就会释放掉被调用对象的锁，然后进入等待状态(wait set).
        3. 当线程调用wait后进入等待状态时，它就可以等待其他线程调用相同对象的notify或者notifyAll方法来使得自己被唤醒.
        4. 一旦这个线程被其他线程唤醒后，该线程就会与其他线程一同开始竞争这个对象的所(公平竞争)；只有当该线程获取到这个对象的所后才会，线程继续往下执行.
        5. 调用wait方法的代码片段需要放在一个Synchronized块或者时Synchronized方法中，这样才能到确保线程在调用wait方法前已经获取到对象的的所.
        6. 当调用对象的notify方法时，它会随机的唤醒该对象等待集合(wait set)中的任意一个线程. 当某个线程被唤醒后，它就会与其他线程一同竞争对象的锁.
        7. 当调用notifyAll方法时，它会唤醒该对象等待集合(wait set) 中的所有线程. 这些线程被唤醒后，有会开始竞争对象的锁.
        8. 在某一时刻，只有一个线程可以拥有对象的锁.

 */

public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();

        synchronized (obj){
            obj.wait();
        }
    }
}
