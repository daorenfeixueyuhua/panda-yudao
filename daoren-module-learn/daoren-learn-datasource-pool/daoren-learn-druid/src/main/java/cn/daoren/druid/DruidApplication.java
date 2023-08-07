package cn.daoren.druid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@MapperScan(value = {"cn.daoren.druid.dal.mysql"})
@EnableTransactionManagement
@SpringBootApplication
public class DruidApplication {
    public static void main(String[] args) {
        SpringApplication.run(DruidApplication.class, args);
    }
}
