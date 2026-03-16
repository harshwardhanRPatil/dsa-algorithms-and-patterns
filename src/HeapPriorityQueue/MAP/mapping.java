package Heap.MAP;

import java.util.HashMap;
import java.util.Map;

public class mapping {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                map.put(Thread.currentThread().getName() + i, "Value" + i);
            }
        };
        Thread thread1 = new Thread(task, "Thread1-");
        Thread thread2 = new Thread(task, "Thread2-");

        thread1.start();
        thread2.start();

//
////            thread1.join();
////            thread2.join();

        // The size will always be 2000 due to thread safety.
        System.out.println("ConcurrentHashMap size: " + map.size());
    }
}
