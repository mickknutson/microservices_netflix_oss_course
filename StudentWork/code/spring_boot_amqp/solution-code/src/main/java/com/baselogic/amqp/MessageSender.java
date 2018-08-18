package com.baselogic.amqp;

import com.baselogic.domain.Order;

public interface MessageSender {
    void sendOrderMessage(Order toSend);
}
