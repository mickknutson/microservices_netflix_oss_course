package com.baselogic.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.baselogic.domain.Order;

@Component
public class MyListener {

  @RabbitListener(queues="${queue.name}")
  public void onMessage(Order order) {
    System.out.println("Message received - Order Details:" + order);
  }
}
