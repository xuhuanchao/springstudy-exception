package com.xhc.springtest.config;

import com.xhc.springtest.exception.resolver.GolbalExceptionResolver;
import com.xhc.springtest.exception.resolver.SelfExceptionResolver;
import com.xhc.springtest.exception.resolver.SelfSimpleExceptionResolver;
import com.xhc.springtest.config.properties.SelfExceptionResolverProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@SpringBootConfiguration
@EnableConfigurationProperties({SelfExceptionResolverProperties.class})
public class ExceptionConfig {

    private static Logger logger = LoggerFactory.getLogger(ExceptionConfig.class);

//    /**
//     * 自定义异常处理器配置
//     * @return
//     */
//    @Bean
//    public SelfExceptionResolverProperties selfExceptionResolverProperties() {
//        SelfExceptionResolverProperties selfExceptionResolverProperties = new SelfExceptionResolverProperties();
//        return selfExceptionResolverProperties;
//    }



//    @Bean
    public HandlerExceptionResolver getExceptionResolvers(){
        GolbalExceptionResolver resolver = new GolbalExceptionResolver();
        List<HttpMessageConverter<?>> list = new ArrayList();
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setDefaultCharset(Charset.forName("UTF-8"));
        list.add(messageConverter);
        resolver.setOrder(0);
        resolver.setMessageConverters(list);
        return resolver;
    }


    /**
     * SelfExceptionResolver
     * @return
     */
//    @Bean
    public SelfExceptionResolver selfExceptionResolver() {
        SelfExceptionResolver selfExceptionResolver = new SelfExceptionResolver();
        return selfExceptionResolver;
    }

    /**
     * 初始化自定义异常
     * SimpleMappingExceptionResolver
     * @return
     */
    @Bean
    public SelfSimpleExceptionResolver selfSimpleExceptionResolver (
            SelfExceptionResolverProperties selfExceptionResolverProperties) throws Exception{
        SelfSimpleExceptionResolver simpleResolver = new SelfSimpleExceptionResolver();
        simpleResolver.setOrder(-100);

        List<String> excludedExceptions = selfExceptionResolverProperties.getExcludedExceptions();
        if(excludedExceptions != null && excludedExceptions.size() > 0){
            Class<?>[] excludedExceptionClass = new Class[excludedExceptions.size()];
            for(int i=0, j=excludedExceptions.size(); i<j; i++){
                try {
                    Class<?> exceptionClass = Class.forName(excludedExceptions.get(i));
                    excludedExceptionClass[i] = exceptionClass;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    logger.error("初始化自定义异常处理器失败", e);
                    throw e;
                }
            }
            simpleResolver.setExcludedExceptions(excludedExceptionClass);
        }

        InputStream is = this.getClass().getClassLoader().getResourceAsStream(selfExceptionResolverProperties.getExceptionViewMapPropertiesPath());
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        simpleResolver.setExceptionMappings(properties);

        simpleResolver.setDefaultErrorView("exception");

        return simpleResolver;
    }
}
