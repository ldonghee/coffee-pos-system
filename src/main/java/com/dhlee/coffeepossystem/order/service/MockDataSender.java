package com.dhlee.coffeepossystem.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.dhlee.coffeepossystem.order.dto.OrderData;

@Component
public class MockDataSender {
	private static final String MOCK_API_URL = "https://mockapi.example.com/orders";

	private final WebClient webClient;

	public MockDataSender(WebClient webClient) {
		this.webClient = webClient;
	}

	public void send(OrderData orderData) {
		webClient.post()
			  .uri(MOCK_API_URL)
			  .bodyValue(orderData)
			  .retrieve()
			  .bodyToMono(String.class)
			  .block();

	}
}
