package cn.daoren.demo.userservice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author peng_da
 * @since 2023/5/11 16:06
 */
@Data
@ConfigurationProperties(prefix = "custom")
public class CustomProperties {
    private String serviceName;
    private String servicePort;
}
