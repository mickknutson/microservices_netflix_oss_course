package com.baselogic.hello;

import com.baselogic.hello.filters.post.ElapsedTimeLoggerFilter;
import com.baselogic.hello.filters.pre.RequestLoggerFilter;
import com.baselogic.hello.filters.pre.StartTimerFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


@SpringBootApplication

// add the annotation for Eureka
@EnableZuulProxy
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    // Request logger filter
    @Bean
    public RequestLoggerFilter requestLoggerFilter() {
        return new RequestLoggerFilter();
    }

    // Start Timer Filter
    @Bean
    public StartTimerFilter startTimerFilter() {
        return new StartTimerFilter();
    }

    // Elapsed Time Filter
    @Bean
    public ElapsedTimeLoggerFilter elapsedTimeLoggerFilter() {
        return new ElapsedTimeLoggerFilter();
    }

}