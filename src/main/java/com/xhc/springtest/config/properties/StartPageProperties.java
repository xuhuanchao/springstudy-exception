package com.xhc.springtest.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 起始页配置信息
 */
@Component
@ConfigurationProperties("startpage")
public class StartPageProperties {

    private List<String> links = new ArrayList<>();

    private String message;

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
