package com.baselogic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceServiceApp.class, args);
	}
}
