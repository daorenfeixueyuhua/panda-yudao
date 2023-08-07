package cn.daoren.lock;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) {
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
                new ThreadFactory() {
                    private AtomicInteger counter = new AtomicInteger(0);

                    @Override
                    public Thread newThread(Runnable r) {
                        final Thread thread = new Thread(r);
                        thread.setName("reentrant-exec-" + counter.incrementAndGet());
                        thread.setDaemon(true);
                        return thread;
                    }
                });
        final ReentrantRun reentrantRun = new ReentrantRun();
        executor.execute(reentrantRun::run);
        executor.execute(reentrantRun::run);
    }
}

class ReentrantRun {
    private Lock lock = new ReentrantLock();

    public void run() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        } finally {
            lock.unlock();
        }
    }
}