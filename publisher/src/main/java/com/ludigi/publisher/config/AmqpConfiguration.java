package com.ludigi.publisher.config;

import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AmqpConfiguration {

    @Bean
    public TopicExchange exampleTopicExchange(@Value("${amqp.exchange.example}") final String exchangeName) {
        return ExchangeBuilder.topicExchange(exchangeName).durable(false).build();
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter(); }
}
