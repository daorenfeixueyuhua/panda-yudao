package cn.daoren.druid.framework.log.filter;

import cn.daoren.druid.framework.log.context.PandaDruidSqlLogHolder;
import cn.daoren.druid.framework.log.runner.WebLogRunner;
import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Web Log Filter
 *
 * @author peng_da
 * @since 2023/7/10 11:35
 */
@Slf4j
@WebFilter("web-log-filter")
public class WebLogFilter extends OncePerRequestFilter {
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    private ThreadPoolExecutor logExecutor = null;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        PandaDruidSqlLogHolder.threadAnalysisExecuteSQLs.set(new Stack<>());
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = uri.substring(contextPath.length());
        //静态资源 跳过
        if (url.contains(".")) {
            filterChain.doFilter(request, response);
            return;
        }
//		输出请求体
        String requestBody = "";
        String requestContentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        final Map<String, String[]> parameterMap = request.getParameterMap();
        boolean hasError = false;
        Exception error = null;
        try {
            filterChain.doFilter(request, response);
            endTime = LocalDateTime.now();

        } catch (Exception e) {
            hasError = true;
            error = e;
        } finally {
//            log.info("start time: " + startTime + "\tend time: " + endTime);
//            log.info("request url: {}", request.getRequestURL());
//            log.info("request params: {}", parameterMap.toString());
//            log.info("request IP: {}", request.getRemoteAddr());
//            final Stack<PandaDruidSqlLogBean> sqlLogBeans = PandaDruidSqlLogHolder.threadAnalysisExecuteSQLs.get();
//
//            StringBuilder sb = new StringBuilder();
//            sqlLogBeans.forEach(sql -> {
//                sb.append(sql.doDealSql()).append("\n");
//            });
//            log.info("execute sql: \n" + sb.toString());
//            if (hasError) {
//                log.error(error.getMessage(), error);
//            }
            if (logExecutor == null) {
                logExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
                        new SynchronousQueue<>(), new ThreadFactoryBuilder().setDaemon(true).setNamePrefix("panda-web-log-exec-").build());
            }
            final Stack<PandaDruidSqlLogBean> sqlLogBeans = PandaDruidSqlLogHolder.threadAnalysisExecuteSQLs.get();

            List<String> listSQL = new ArrayList<>();
            sqlLogBeans.forEach(sql -> {
                listSQL.add(sql.doDealSql());
            });
            final PandaWebLogBean webLogBean = PandaWebLogBean.builder()
                    .startTime(startTime)
                    .endTime(endTime)
                    .url(request.getRequestURI())
                    .remoteAddress(request.getRemoteAddr())
                    .sql(listSQL)
                    .exception(error).build();
            logExecutor.execute(WebLogRunner.builder().logBean(webLogBean).build());
            PandaDruidSqlLogHolder.threadAnalysisExecuteSQLs.set(null);
        }
    }

}
