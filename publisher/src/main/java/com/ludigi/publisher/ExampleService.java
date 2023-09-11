package com.ludigi.publisher;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
class ExampleService {
    private final ExampleEventPublisher eventPublisher;
    private final ExampleEntityRepository exampleEntityRepository;
    private final Random random = new Random();

    public ExampleService(ExampleEventPublisher eventPublisher, ExampleEntityRepository exampleEntityRepository) {
        this.eventPublisher = eventPublisher;
        this.exampleEntityRepository = exampleEntityRepository;
    }

    public void doExample() {
        UUID id = UUID.randomUUID();
        int someNumber = random.nextInt(100);
        ExampleEntity entity = new ExampleEntity(id, "Name " + Integer.toHexString(someNumber), "Description " + someNumber);
        exampleEntityRepository.save(entity);
        ExampleEvent exampleEvent = new ExampleEvent(id, entity.getName());
        eventPublisher.publish(exampleEvent);
    }

}
