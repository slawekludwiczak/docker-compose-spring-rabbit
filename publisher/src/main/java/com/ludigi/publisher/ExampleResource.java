package com.ludigi.publisher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
class ExampleResource {
    private final ExampleService exampleService;

    public ExampleResource(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping
    ResponseEntity<?> doSomething() {
        exampleService.doExample();
        return ResponseEntity.ok("success");
    }
}
