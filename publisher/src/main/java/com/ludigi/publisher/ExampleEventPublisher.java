package com.ludigi.publisher;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
class ExampleEventPublisher {
    private final AmqpTemplate amqpTemplate;
    private final String exampleTopicExchange;

    public ExampleEventPublisher(AmqpTemplate amqpTemplate,
                                 @Value("${amqp.exchange.example}") String exampleTopicExchange) {
        this.amqpTemplate = amqpTemplate;
        this.exampleTopicExchange = exampleTopicExchange;
    }

    void publish(ExampleEvent exampleEvent) {
        String routingKey = "example.all";
        amqpTemplate.convertAndSend(exampleTopicExchange, routingKey, exampleEvent);
    }
}
