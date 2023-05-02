package com.dhlee.coffeepossystem.common.dto;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderAnalysisData {
	private Long userId;
	private Long menuId;
	private int price;

	@Override
	public String toString() {
		return "OrderAnalysisData{" +
			   "userId=" + userId +
			   ", menuId=" + menuId +
			   ", price=" + price +
			   '}';
	}
}
