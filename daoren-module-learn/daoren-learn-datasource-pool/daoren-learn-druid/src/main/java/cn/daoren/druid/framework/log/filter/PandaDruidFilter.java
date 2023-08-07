package cn.daoren.druid.framework.log.filter;

import cn.daoren.druid.framework.log.context.PandaDruidSqlLogHolder;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ResultSetProxy;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author peng_da
 * @since 2023/7/10 9:47
 */
@Component
@Slf4j
public class PandaDruidFilter extends FilterEventAdapter {

    @Override
    protected void statementExecuteBefore(StatementProxy statement, String sql) {
        PandaDruidSqlLogHolder.addPandaDruidSqlLog(sql, statement);
    }

    @Override
    protected void statementExecuteAfter(StatementProxy statement, String sql, boolean result) {
        PandaDruidSqlLogHolder.updatePandaDruidSqlLog();
    }

    @Override
    protected void statementExecuteQueryBefore(StatementProxy statement, String sql) {
        PandaDruidSqlLogHolder.addPandaDruidSqlLog(sql, statement);
    }

    @Override
    protected void statementExecuteQueryAfter(StatementProxy statement, String sql, ResultSetProxy resultSet) {
        PandaDruidSqlLogHolder.updatePandaDruidSqlLog();
    }

}
