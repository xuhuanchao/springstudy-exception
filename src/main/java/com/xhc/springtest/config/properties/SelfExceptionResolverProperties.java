package com.xhc.springtest.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义异常处理配置，不处理的异常和异常对应视图关系
 */
@ConfigurationProperties("self-exception-resolver-properties")
public class SelfExceptionResolverProperties {

    private List<String> excludedExceptions = new ArrayList<>();

    private String exceptionViewMapPropertiesPath;


    public List<String> getExcludedExceptions() {
        return excludedExceptions;
    }

    public void setExcludedExceptions(List<String> excludedExceptions) {
        this.excludedExceptions = excludedExceptions;
    }

    public String getExceptionViewMapPropertiesPath() {
        return exceptionViewMapPropertiesPath;
    }

    public void setExceptionViewMapPropertiesPath(String exceptionViewMapPropertiesPath) {
        this.exceptionViewMapPropertiesPath = exceptionViewMapPropertiesPath;
    }
}
