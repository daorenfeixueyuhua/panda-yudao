package cn.daoren.task.job;


import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class DemoJob {

    private final AtomicInteger counts = new AtomicInteger();

    @XxlJob(value = "demoJob")
    public void execute() throws Exception {
        log.info("[execute][定时第 ({}) 次执行]", counts.incrementAndGet());
    }
}
