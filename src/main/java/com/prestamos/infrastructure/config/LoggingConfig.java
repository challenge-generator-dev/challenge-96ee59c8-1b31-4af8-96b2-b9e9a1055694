package com.prestamos.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Configuration
public class LoggingConfig {

    private static final Logger logger = LoggerFactory.getLogger(LoggingConfig.class);
    private static final String REQUEST_ID_HEADER = "X-Request-ID";
    private static final String REQUEST_ID_ATTR = "requestId";

    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> requestLoggingFilter() {
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLoggingFilter());
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 10);
        registrationBean.setName("requestLoggingFilter");
        return registrationBean;
    }

    @Bean
    public LoggingProperties loggingProperties() {
        return new LoggingConfig.LoggingProperties();
    }

    public static class RequestLoggingFilter extends OncePerRequestFilter {

        private static final Logger filterLogger = LoggerFactory.getLogger(RequestLoggingFilter.class);

        @Override
        protected void doFilterInternal(HttpServletRequest request, 
                                        HttpServletResponse response, 
                                        FilterChain filterChain) throws ServletException, IOException {
            
            String requestId = request.getHeader(REQUEST_ID_HEADER);
            if (requestId == null || requestId.isBlank()) {
                requestId = UUID.randomUUID().toString();
            }
            
            request.setAttribute(REQUEST_ID_ATTR, requestId);
            long startTime = System.currentTimeMillis();
            
            String method = request.getMethod();
            String uri = request.getRequestURI();
            String queryString = request.getQueryString();
            String clientIp = getClientIp(request);
            
            filterLogger.info("[{}] INICIO - {} {} {} - IP: {}", 
                    requestId, method, uri, 
                    queryString != null ? "?" + queryString : "",
                    clientIp);
            
            try {
                filterChain.doFilter(request, response);
            } finally {
                long duration = System.currentTimeMillis() - startTime;
                int status = response.getStatus();
                
                String logLevel = status >= 400 ? "WARN" : "INFO";
                if (status >= 500) {
                    logLevel = "ERROR";
                }
                
                filterLogger.info("[{}] FIN - {} {} - Estado: {} - Duración: {}ms",
                        requestId, method, uri, status, duration);
                
                if (duration > 1000) {
                    filterLogger.warn("[{}] PETICIÓN LENTA detectada - {} {} took {}ms",
                            requestId, method, uri, duration);
                }
                
                response.setHeader(REQUEST_ID_HEADER, requestId);
            }
        }

        private String getClientIp(HttpServletRequest request) {
            String xForwardedFor = request.getHeader("X-Forwarded-For");
            if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
                return xForwardedFor.split(",")[0].trim();
            }
            
            String xRealIp = request.getHeader("X-Real-IP");
            if (xRealIp != null && !xRealIp.isEmpty()) {
                return xRealIp;
            }
            
            return request.getRemoteAddr();
        }
    }

    public static class LoggingProperties {
        private boolean requestIdEnabled = true;
        private boolean logRequestBody = false;
        private boolean logResponseBody = false;
        private long slowRequestThresholdMs = 1000;
        private String[] excludedPaths = {"/actuator/health", "/actuator/info"};

        public boolean isRequestIdEnabled() {
            return requestIdEnabled;
        }

        public void setRequestIdEnabled(boolean requestIdEnabled) {
            this.requestIdEnabled = requestIdEnabled;
        }

        public boolean isLogRequestBody() {
            return logRequestBody;
        }

        public void setLogRequestBody(boolean logRequestBody) {
            this.logRequestBody = logRequestBody;
        }

        public boolean isLogResponseBody() {
            return logResponseBody;
        }

        public void setLogResponseBody(boolean logResponseBody) {
            this.logResponseBody = logResponseBody;
        }

        public long getSlowRequestThresholdMs() {
            return slowRequestThresholdMs;
        }

        public void setSlowRequestThresholdMs(long slowRequestThresholdMs) {
            this.slowRequestThresholdMs = slowRequestThresholdMs;
        }

        public String[] getExcludedPaths() {
            return excludedPaths;
        }

        public void setExcludedPaths(String[] excludedPaths) {
            this.excludedPaths = excludedPaths;
        }
    }
}