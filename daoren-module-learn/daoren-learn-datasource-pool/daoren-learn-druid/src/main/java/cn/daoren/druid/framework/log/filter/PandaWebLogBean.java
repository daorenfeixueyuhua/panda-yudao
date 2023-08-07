package cn.daoren.druid.framework.log.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * web日志实体
 *
 * @author peng_da
 * @since 2023/7/11 9:31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PandaWebLogBean {
    /**
     * 请求Url
     */
    private String url;
    /**
     * 请求参数
     */
    private Map<String, Object> parameters;
    /**
     * 远程服务调用地址
     */
    private String remoteAddress;
    /**
     * 请求开始时间
     */
    private LocalDateTime startTime;
    /**
     * 请求结束时间
     */
    private LocalDateTime endTime;
    /**
     * 相关执行SQL
     */
    private List<String> sql;
    /**
     * 请求异常
     */
    private Exception exception;
}
