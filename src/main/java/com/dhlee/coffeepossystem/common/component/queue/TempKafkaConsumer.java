package com.dhlee.coffeepossystem.common.component.queue;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TempKafkaConsumer {
	@KafkaListener(topics = "order", groupId = "coffee-order")
	public void consume(String message) {
		log.info("Consumed message : " + message);
	}
}
