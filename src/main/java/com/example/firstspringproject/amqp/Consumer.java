package com.example.firstspringproject.amqp;

import org.springframework.stereotype.Component;

@Component
public class Consumer {
    public void receiveMessage(int message){
        System.out.println("RECEIVED MESSAGE: " + message);
    }
}
