package cn.daoren.admin.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class DemoScheduler {

    private final AtomicInteger counter = new AtomicInteger();

    //    @Scheduled(cron = "0/10 * * * * ?")
    public void run() {
        log.info("【开始执行任务，当前任务序列:{}】", counter.incrementAndGet());
    }
}
