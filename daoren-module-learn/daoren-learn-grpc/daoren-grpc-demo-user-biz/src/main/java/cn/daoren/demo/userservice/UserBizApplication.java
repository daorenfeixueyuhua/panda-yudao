package cn.daoren.demo.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author peng_da
 * @since 2023/5/11 16:08
 */
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackages = "cn.daoren.**.properties")
@SpringBootApplication
public class UserBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserBizApplication.class, args);
    }
}
