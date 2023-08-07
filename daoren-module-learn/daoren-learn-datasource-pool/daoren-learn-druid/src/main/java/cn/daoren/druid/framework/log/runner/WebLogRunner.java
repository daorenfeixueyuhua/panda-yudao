package cn.daoren.druid.framework.log.runner;

import cn.daoren.druid.framework.log.filter.PandaWebLogBean;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author peng_da
 * @since 2023/7/11 10:10
 */
@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebLogRunner implements Runnable {
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private PandaWebLogBean logBean;

    @Override
    public void run() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        try {
            final String s = JSONUtil.toJsonPrettyStr(logBean);
            sb.append(s);
            log.info(sb.toString());
        } catch (Exception e) {
            log.error("输出日志错误", e);
        }

    }
}
