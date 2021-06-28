package demo5;

public class ThreadLocalTest1 {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        threadLocal.set("aaaaaa");

        System.out.println(threadLocal.get());
    }
}
