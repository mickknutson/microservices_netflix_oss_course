package com.baselogic.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ADMIN:
 * http://localhost:15672
 *
 * BROKER:
 * http://localhost:5672
 */
@Configuration
public class MessagingConfig {

    @Value("${queue.name}")
    private String queueName;

    @Bean
	public Queue queueName() {
		return new Queue(queueName);
	}

    @Value("${queue.demo.name}")
    private String queueDemoName;

    @Bean
	public Queue queueDemoName() {
		return new Queue(queueDemoName);
	}
}
