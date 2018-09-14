package com.baselogic.netflix.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class App {

    /**
     * NOTE: This exception is thrown:
     *      Unable to start ServletWebServerApplicationContext due to missing ServletWebServerFactory bean
     * by having this declaration:
     *      SpringApplication.run(SomeOtherCLass_Not_App.class, args);
     */
    // add a main method and start the SpringBoot App
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}