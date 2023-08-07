package cn.daoren.druid.framework.log.config;

import cn.daoren.druid.framework.log.filter.WebLogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author peng_da
 * @since 2023/7/10 14:48
 */
@Configuration
public class WebLogConfig {

    @Bean
    public FilterRegistrationBean<Filter> registrationBean() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        final WebLogFilter filter = new WebLogFilter();
        registrationBean.setFilter(filter);
        return registrationBean;
    }
}
