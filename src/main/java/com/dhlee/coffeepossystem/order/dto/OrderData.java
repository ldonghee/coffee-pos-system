package com.dhlee.coffeepossystem.order.dto;

import lombok.Getter;

import com.dhlee.coffeepossystem.order.domain.Order;

@Getter
public class OrderData {
	private Long userId;
	private Long menuId;
	private int price;

	public OrderData(Order order) {
		this.userId = order.getUser().getId();
		this.menuId = order.getCoffees().get(0).getId();
		this.price = order.getTotalPrice();
	}
}
