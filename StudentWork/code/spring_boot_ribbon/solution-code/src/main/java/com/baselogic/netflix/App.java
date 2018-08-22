package com.baselogic.netflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication

// Enable Eureka
@EnableDiscoveryClient

// Ribbon enabled client
@EnableFeignClients
// @LoadBalanced
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
