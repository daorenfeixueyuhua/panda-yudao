package cn.daoren;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author peng_da
 * @since 2023/7/17 15:23
 */
public class IntegerCacheTest {
    private static final Logger log = LoggerFactory.getLogger(IntegerCacheTest.class);

    public static void main(String[] args) {
        // 被编译为 java.lang.Integer.valueOf(int)
        Integer a1 = 126, a2 = 126;
        Integer b1 = 129, b2 = 129;
        System.out.println(a1 == a2);
        System.out.println(b1 == b2);
//        wrong();
        right();
    }

    public static void wrong() {
        try {
            log.info("try");
            //异常丢失
            throw new RuntimeException("try");
        } finally {
            log.info("finally");
            throw new RuntimeException("finally");
        }
    }

    public static void right() {
        try {
            log.info("try");
            throw new RuntimeException("try");
        } finally {
            log.info("finally");
            try {
                throw new RuntimeException("finally");
            } catch (Exception ex) {
                log.error("finally", ex);
            }
        }
    }
}
