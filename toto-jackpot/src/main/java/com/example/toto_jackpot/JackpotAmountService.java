package com.example.toto_jackpot;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JackpotAmountService {
    
    private final KafkaTemplate<String, JackpotAmount> kafkaTemplate;
    private static final String TOPIC = "jackpot-topic";

    public void publishNewJackpot(JackpotAmount jackpot) {
        kafkaTemplate.send(TOPIC, jackpot.date(), jackpot);
    }

}
