package cn.daoren.druid.framework.log.context;

import cn.daoren.druid.framework.log.filter.PandaDruidSqlLogBean;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Stack;

/**
 * @author peng_da
 * @since 2023/7/10 11:22
 */
@Slf4j
public class PandaDruidSqlLogHolder {
    public final static ThreadLocal<Stack<PandaDruidSqlLogBean>> threadAnalysisExecuteSQLs = new ThreadLocal<>();

    public static void addPandaDruidSqlLog(String sql, StatementProxy statement) {
        if (threadAnalysisExecuteSQLs.get() == null) {
            synchronized (threadAnalysisExecuteSQLs) {
                if (threadAnalysisExecuteSQLs.get() == null) {
                    threadAnalysisExecuteSQLs.set(new Stack<>());
                }
            }
        }
        final PandaDruidSqlLogBean logBean = PandaDruidSqlLogBean.builder()
                .sql(sql)
                .parameters(statement.getParameters())
                .dbType(statement.getConnectionProxy().getDirectDataSource().getDbType())
                .startTime(LocalDateTime.now())
                .build();
        threadAnalysisExecuteSQLs.get().push(logBean);
    }

    public static void updatePandaDruidSqlLog() {
        final PandaDruidSqlLogBean logBean = threadAnalysisExecuteSQLs.get().peek();
        logBean.setEndTime(LocalDateTime.now());
    }

    public void init() {
        threadAnalysisExecuteSQLs.set(new Stack<>());
    }

    public void clear() {
        threadAnalysisExecuteSQLs.set(null);
    }

    public void counter() {
        log.debug("counter threadAnalysisExecuteSQLs    : {}", threadAnalysisExecuteSQLs.get().size());
    }
}
