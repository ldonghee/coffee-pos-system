package com.dhlee.coffeepossystem.common.component.queue;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.dhlee.coffeepossystem.common.config.queue.KafkaTopic;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {
	private final KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {
		log.info("Produce message : " + message);
		this.kafkaTemplate.send(KafkaTopic.ORDER.getTopicName(), message);
	}
}
