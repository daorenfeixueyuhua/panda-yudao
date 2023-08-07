package cn.daoren.task.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class DemoScheduler {

    private final AtomicInteger counter = new AtomicInteger();

    //    @Scheduled(cron = "0/10 * * * * ?")
    public void run() {
        log.info("执行次数{}", counter.incrementAndGet());
    }
}
