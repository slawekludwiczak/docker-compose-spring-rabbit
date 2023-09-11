package com.ludigi.subscriber.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
class AmqpConfiguration {

    @Bean
    public TopicExchange exampleTopicExchange(@Value("${amqp.exchange.example}") final String exchangeName) {
        return ExchangeBuilder.topicExchange(exchangeName).durable(false).build();
    }

    @Bean
    public Queue gamificationQueue(
            @Value("${amqp.queue.example}") final String queueName) {
        return QueueBuilder.nonDurable(queueName).build();
    }

    @Bean
    public Binding exampleBinding(final Queue exampleQueue,
                                  final TopicExchange exampleExchange) {
        return BindingBuilder.bind(exampleQueue)
                .to(exampleExchange)
                .with("example.all");
    }

    //Potrzebny jeżeli chcemy deserializować JSONa
    //Dla wydajności lepiej korzystać z formatów binarnych, np. protobuf
    @Bean
    public MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        final MappingJackson2MessageConverter jsonConverter = new MappingJackson2MessageConverter();
        jsonConverter.getObjectMapper().registerModule(
                new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
        factory.setMessageConverter(jsonConverter);
        return factory;
    }

    //potrzebne do obsługi zdarzeń przez adnotacje?
    @Bean
    public RabbitListenerConfigurer rabbitListenerConfigurer(
            final MessageHandlerMethodFactory messageHandlerMethodFactory) {
        return (c) -> c.setMessageHandlerMethodFactory(messageHandlerMethodFactory);
    }
}
