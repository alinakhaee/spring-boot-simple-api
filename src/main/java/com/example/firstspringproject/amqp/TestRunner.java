package com.example.firstspringproject.amqp;

import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestRunner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Consumer consumer;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(AmqpConfiguration.topicExchangeName, "foo.bar.baz",2);
    }

}