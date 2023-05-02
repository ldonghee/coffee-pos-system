package com.dhlee.coffeepossystem.order.dto;

import lombok.Getter;

@Getter
public class OrderRequest {
	private Long userId;
	private Long menuId;

	public OrderRequest(Long userId, Long menuId) {
		this.userId = userId;
		this.menuId = menuId;
	}
}
