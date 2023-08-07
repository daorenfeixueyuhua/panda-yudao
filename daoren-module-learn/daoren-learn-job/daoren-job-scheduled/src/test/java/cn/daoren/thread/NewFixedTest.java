package cn.daoren.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedTest {

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {

                }
            });
        }
    }
}
