package com.dhlee.coffeepossystem.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.dhlee.coffeepossystem.order.dto.OrderRequest;
import com.dhlee.coffeepossystem.order.dto.OrderResponse;
import com.dhlee.coffeepossystem.order.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/order")
public class OrderController {
	private final OrderService orderService;

	// 커피 주문/결제하기 API
	@PostMapping
	public ResponseEntity<OrderResponse> order(@RequestBody OrderRequest orderRequest) {
		OrderResponse order = orderService.orderAndPay(orderRequest);
		return ResponseEntity.ok().body(order);
	}
}
