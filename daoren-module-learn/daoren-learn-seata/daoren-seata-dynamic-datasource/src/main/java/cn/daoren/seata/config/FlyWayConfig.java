package cn.daoren.seata.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Map;

/**
 * 自定义执行
 *
 * @author peng_da
 * @since 2023/8/3 15:18
 */
@Slf4j
@Configuration
@EnableTransactionManagement
public class FlyWayConfig {

    private DataSource dataSource;
    @Value("${spring.flyway.locations}")
    private String SQL_LOCATION;
    @Value("${spring.flyway.table}")
    private String VERSION_TABLE;
    @Value("${spring.flyway.baseline-on-migrate}")
    private boolean BASELINE_ON_MIGRATE;
    @Value("${spring.flyway.out-of-order}")
    private boolean OUT_OF_ORDER;
    @Value("${spring.flyway.validate-on-migrate}")
    private boolean VALIDATE_ON_MIGRATE;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void migrate() {
        log.debug("Dynamic Datasource Migrating");
        SQL_LOCATION = SQL_LOCATION.split("/")[0];
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        final Map<String, DataSource> dataSources = ds.getDataSources();
        dataSources.forEach((k, v) -> {
            log.debug("Executing datasource migration for {}", k);
            final Flyway flyway = Flyway.configure()
                    .dataSource(v)
                    .locations(SQL_LOCATION + "/" + k)
                    .baselineOnMigrate(BASELINE_ON_MIGRATE)
                    .table(VERSION_TABLE)
                    .outOfOrder(OUT_OF_ORDER)
                    .validateOnMigrate(VALIDATE_ON_MIGRATE)
                    .load();
            flyway.migrate();
            log.debug("Executing datasource migration is successful for {}", k);
        });
    }
}
