package com.baselogic.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.baselogic.domain.Order;

@Component
public class MessageSenderImpl implements MessageSender {
	
	@Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${queue.name}")
    private String queueName;

    @Override
	  public void sendOrderMessage(Order toSend) {
	    System.out.println("Sending message with order - " + toSend);
//	    amqpTemplate.convertAndSend("myqueue1", toSend);
	    amqpTemplate.convertAndSend(queueName, toSend);

	  }
}