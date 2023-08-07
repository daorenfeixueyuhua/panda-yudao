package cn.daoren.lock;

import cn.iocoder.yudao.framework.redis.config.YudaoRedisAutoConfiguration;
import cn.iocoder.yudao.framework.test.config.RedisTestConfiguration;
import org.junit.jupiter.api.Test;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {RedisLockTest.Application.class}
)
@ActiveProfiles({"unit-test"})
public class RedisLockTest {


    @Test
    public void run1() {

    }

    @Import({RedisTestConfiguration.class, RedisAutoConfiguration.class, YudaoRedisAutoConfiguration.class, RedissonAutoConfiguration.class})
    public static class Application {
        public Application() {
        }
    }
}
