package com.dhlee.coffeepossystem.common.aop.analysis;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.dhlee.coffeepossystem.common.component.queue.KafkaProducer;
import com.dhlee.coffeepossystem.common.dto.OrderAnalysisData;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class DataAnalysisAspect {
	private final ObjectMapper objectMapper = new ObjectMapper();

	private final KafkaProducer producer;

	@AfterReturning(value = "@annotation(com.dhlee.coffeepossystem.common.aop.analysis.DataAnalysis)", returning = "result")
	public void doAnalysis(JoinPoint joinPoint, Object result) throws RuntimeException {

		OrderAnalysisData data = objectMapper.convertValue(result, OrderAnalysisData.class);
		log.info("Data Send For Analysis : " + data.toString());
		producer.sendMessage(data.toString());
	}
}
