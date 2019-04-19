package com.xhc.springtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan
public class SpringTestApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringTestApplication.class, args);

    }


}
