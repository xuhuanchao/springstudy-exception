package com.xhc.springtest.config;

import com.xhc.springtest.config.properties.StartPageProperties;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
//@EnableConfigurationProperties({StartPageProperties.class})
public class BeanConfig {

//    /**
//     * 首页配置信息
//     * @return
//     */
//    @Bean
//    public StartPageProperties getStartPageProperties(){
//        return new StartPageProperties();
//    }
}
