package com.example.buying_agent_consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BuyingService {
    private static final String TOPIC = "jackpot-topic";

    @KafkaListener(topics = TOPIC, groupId = TOPIC)
    public void consumeNewJackpot(String jackpot) {
        System.out.println("New jackpot event: " + jackpot);
    }
}