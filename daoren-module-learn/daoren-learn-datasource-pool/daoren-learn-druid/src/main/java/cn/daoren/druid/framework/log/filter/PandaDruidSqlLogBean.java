package cn.daoren.druid.framework.log.filter;

import com.alibaba.druid.DbType;
import com.alibaba.druid.proxy.jdbc.JdbcParameter;
import com.alibaba.druid.sql.SQLUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author peng_da
 * @since 2023/7/10 10:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PandaDruidSqlLogBean {
    private String dbType;
    /**
     * 参数
     */
    private Map<Integer, JdbcParameter> parameters;
    /**
     * 执行SQL
     */
    private String sql;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 转换为可执行SQL
     *
     * @return java.lang.String
     * @author peng_da
     * @since 2023/7/10 11:15
     */
    public String doDealSql() {
        List<Object> parameterList = new ArrayList<>();
        for (Integer p : parameters.keySet()) {
            parameterList.add(parameters.get(p).getValue());
        }
        try {
            final String formattedSQL = SQLUtils.format(sql, DbType.valueOf(dbType), parameterList);
            boolean isBlank = false;
            boolean isQuote = false;
            final StringBuilder sb = new StringBuilder();
            for (char c : formattedSQL.trim().toCharArray()) {
                switch (c) {
                    case ' ':
                        if (isQuote) {
                            sb.append(c);
                        } else {
                            if (!isBlank) {
                                isBlank = true;
                                sb.append(c);
                            }
                        }
                        break;
                    case '\n':
                    case '\t':
                        sb.append(' ');
                        break;
                    case '\'':
                        if (!isQuote) {
                            isBlank = true;
                            isBlank = false;
                        } else {
                            isQuote = false;
                        }
                    default:
                        sb.append(c);
                        isBlank = false;
                        break;
                }
            }
            return sb.toString();
        } catch (Exception e) {
            String sqlTemp = sql;
            for (Object obj : parameterList) {
                sqlTemp = sqlTemp.replaceFirst("?", "'" + (String) obj + "'");
            }
            return sqlTemp;
        }
    }

}
