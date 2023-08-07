package cn.daoren.demo.userservice;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class UserServiceApplication {
    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
        new CountDownLatch(1).await();
    }
}
