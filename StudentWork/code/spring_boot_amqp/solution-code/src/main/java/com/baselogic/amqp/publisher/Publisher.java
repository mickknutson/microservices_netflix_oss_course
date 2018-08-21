package com.baselogic.amqp.publisher;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queueDemoName;

    @Scheduled(fixedDelay = 1_000, initialDelay = 500)
    public void send() {
        String message = "Hello World!";
        this.template.convertAndSend(queueDemoName.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
