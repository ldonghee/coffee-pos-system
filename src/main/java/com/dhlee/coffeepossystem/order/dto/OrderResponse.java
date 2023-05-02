package com.dhlee.coffeepossystem.order.dto;

import java.time.LocalDateTime;

import lombok.Getter;

import com.dhlee.coffeepossystem.order.domain.Order;
import com.dhlee.coffeepossystem.point.domain.Point;

@Getter
public class OrderResponse {
	private Long orderId;
	private Long userId;
	private Long menuId;
	private int totalPrice;
	private int balancePoint;
	private LocalDateTime orderDateTime;

	public OrderResponse(Long orderId, Long userId, Long menuId, int totalPrice, int balancePoint, LocalDateTime orderDateTime) {
		this.orderId = orderId;
		this.userId = userId;
		this.menuId = menuId;
		this.totalPrice = totalPrice;
		this.balancePoint = balancePoint;
		this.orderDateTime = orderDateTime;
	}

	public static OrderResponse of(Order order, Point point) {
		return new OrderResponse(order.getId(), point.getUser().getId(), order.getCoffees().get(0).getId(), order.getTotalPrice(), point.getPoint(), order.getOrderDateTime());
	}
}
