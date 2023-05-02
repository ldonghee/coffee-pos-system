package com.dhlee.coffeepossystem.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.dhlee.coffeepossystem.coffeeMenu.domain.CoffeeMenu;
import com.dhlee.coffeepossystem.coffeeMenu.service.CoffeeMenuService;
import com.dhlee.coffeepossystem.common.aop.analysis.DataAnalysis;
import com.dhlee.coffeepossystem.common.component.cache.CacheService;
import com.dhlee.coffeepossystem.order.domain.Order;
import com.dhlee.coffeepossystem.order.domain.OrderDetail;
import com.dhlee.coffeepossystem.order.dto.OrderRequest;
import com.dhlee.coffeepossystem.order.dto.OrderResponse;
import com.dhlee.coffeepossystem.order.repository.OrderRepository;
import com.dhlee.coffeepossystem.point.domain.Point;
import com.dhlee.coffeepossystem.point.service.PointService;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderService {
	private final PointService pointService;
	private final CoffeeMenuService coffeeMenuService;
	private final OrderRepository orderRepository;

	@DataAnalysis
	public OrderResponse orderAndPay(OrderRequest orderRequest) {
		Point point = pointService.findPointByUserId(orderRequest.getUserId());
		CoffeeMenu coffeeMenu = coffeeMenuService.findById(orderRequest.getMenuId());

		// 주문
		Order order = saveOrder(point, coffeeMenu);
		// 결재
		point.pay(order.getTotalPrice());

		return OrderResponse.of(order, point);
	}

	private Order saveOrder(Point point, CoffeeMenu coffeeMenu) {
		Order order = new Order(point.getUser());
		OrderDetail orderDetail = new OrderDetail(coffeeMenu);

		orderDetail.setOrder(order);
		order.addOrderDetail(orderDetail);

		return orderRepository.save(order);
	}
}
