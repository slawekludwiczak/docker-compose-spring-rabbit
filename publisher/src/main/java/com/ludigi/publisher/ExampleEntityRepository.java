package com.ludigi.publisher;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface ExampleEntityRepository extends CrudRepository<ExampleEntity, UUID> {
}
