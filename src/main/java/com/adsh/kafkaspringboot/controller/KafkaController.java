package com.adsh.kafkaspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
public class KafkaController {

    private final static String TOPIC_NAME = "my-replicated-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/send")
    public String sendMessage(){
        kafkaTemplate.send(TOPIC_NAME, "key", "This is a message");
        return "send success!";
    }
}
