package com.dhlee.coffeepossystem.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.dhlee.coffeepossystem.coffeeMenu.domain.CoffeeMenu;
import com.dhlee.coffeepossystem.coffeeMenu.service.CoffeeMenuService;
import com.dhlee.coffeepossystem.common.component.CacheService;
import com.dhlee.coffeepossystem.order.domain.Order;
import com.dhlee.coffeepossystem.order.domain.OrderDetail;
import com.dhlee.coffeepossystem.order.dto.OrderData;
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
	private final MockDataSender dataSender;
	private final CacheService cacheService;

	public OrderResponse orderAndPay(OrderRequest orderRequest) {
		Point point = pointService.findPointByUserId(orderRequest.getUserId());
		CoffeeMenu coffeeMenu = coffeeMenuService.findById(orderRequest.getMenuId());

		// 주문
		Order order = saveOrder(point, coffeeMenu);
		// 결재
		point.pay(order.getTotalPrice());
		// 주문 내역 전송
		sendData(order);

		return OrderResponse.of(order, point);
	}

	private Order saveOrder(Point point, CoffeeMenu coffeeMenu) {
		Order order = new Order(point.getUser());
		OrderDetail orderDetail = new OrderDetail(coffeeMenu);

		orderDetail.setOrder(order);
		order.addOrderDetail(orderDetail);
		cacheService.savePopularMenu(coffeeMenu.getId());

		return orderRepository.save(order);
	}

	private void sendData(Order order) {
		try {
			dataSender.send(new OrderData(order));
		} catch (Exception e) {
			log.error("Failed to Send Data : " + e.getMessage());
			// throw new RuntimeException();
		}
	}
}
