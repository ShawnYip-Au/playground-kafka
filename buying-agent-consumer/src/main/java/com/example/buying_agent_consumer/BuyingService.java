package com.example.buying_agent_consumer;

//https://kafka.apache.org/21/javadoc/org/apache/kafka/clients/consumer/ConsumerRecord.html
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BuyingService {
    private static final String TOPIC = "jackpot-topic";

    @KafkaListener(topics = TOPIC, groupId = "${spring.application.name}")
     public void consumeNewJackpot(ConsumerRecord<String, String> record) {
        System.out.println(
            "key: " + record.key() + "\n" +
            "value: " + record.value() + "\n" +
            "topic: " + record.topic() + "\n" +
            "partition: " + record.partition() + "\n" +
            "offset: " + record.offset() + "\n" +
            "timestamp: " + record.timestamp() + "\n" +
            "timestampType: " + record.timestampType() + "\n" +
            "headers: " + record.headers() + "\n" +
            "serializedKeySize: " + record.serializedKeySize() + "\n" +
            "serializedValueSize: " + record.serializedValueSize() + "\n" +
            "leaderEpoch: " + record.leaderEpoch().orElse(null) + "\n"
        );
    }
}