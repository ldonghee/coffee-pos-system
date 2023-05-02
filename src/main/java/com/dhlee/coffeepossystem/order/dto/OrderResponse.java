package com.dhlee.coffeepossystem.order.dto;

import lombok.Getter;

import com.dhlee.coffeepossystem.order.domain.Order;
import com.dhlee.coffeepossystem.point.domain.Point;

@Getter
public class OrderResponse {
	private Long orderId;
	private Long userId;
	private Long menuId;
	private int price;
	private int balancePoint;

	public OrderResponse(Long orderId, Long userId, Long menuId, int price, int balancePoint) {
		this.orderId = orderId;
		this.userId = userId;
		this.menuId = menuId;
		this.price = price;
		this.balancePoint = balancePoint;
	}

	public static OrderResponse of(Order order, Point point) {
		return new OrderResponse(order.getId(), point.getUser().getId(), order.getCoffees().get(0).getId(), order.getTotalPrice(), point.getPoint());
	}
}
