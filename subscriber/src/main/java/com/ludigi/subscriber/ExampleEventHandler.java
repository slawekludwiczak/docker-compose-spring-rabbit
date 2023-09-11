package com.ludigi.subscriber;

import org.springframework.amqp.ImmediateRequeueAmqpException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
class ExampleEventHandler {

    @RabbitListener(queues = "${amqp.queue.example}")
    public void subscribeToEvent(final ExampleEvent exampleEvent) {
        System.out.println(">>> Handling " + exampleEvent);
//        try {
//            Thread.sleep(10_000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        throw new ImmediateRequeueAmqpException("blah blah " + exampleEvent.id());
    }
}
