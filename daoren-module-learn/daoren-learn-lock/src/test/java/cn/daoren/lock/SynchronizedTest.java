package cn.daoren.lock;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedTest {
    public static void main(String[] args) {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16, 16, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
            private volatile AtomicInteger counter = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                final Thread thread = new Thread(r);
                thread.setName("synchronized-thread-" + counter.incrementAndGet());
                return thread;
            }
        });

        for (int i = 0; i < 10; i++) {
            final Run1 run1 = new Run1("Run-" + i);
            threadPoolExecutor.execute(run1);
        }
        threadPoolExecutor.shutdown();
        System.out.println("threadPoolExecutor is shutdown");

    }
}

class Run1 implements Runnable {
    private String name;

    public Run1(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        this.run01();
    }

    public void run01() {
        try {
            synchronized (Run1.class) {
                System.out.println(this.name + " start running " + LocalDateTimeUtil.formatNormal(LocalDateTimeUtil.now()));
                System.out.println(this.name + " is running " + LocalDateTimeUtil.formatNormal(LocalDateTimeUtil.now()));
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(this.name + " end running " + LocalDateTimeUtil.formatNormal(LocalDateTimeUtil.now()));
    }
}
