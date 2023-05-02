package com.dhlee.coffeepossystem.coffeeMenu.dto;

import lombok.Getter;

import com.dhlee.coffeepossystem.coffeeMenu.domain.CoffeeMenu;

@Getter
public class PopularCoffeeMenuResponse {
	private Long menuId;
	private String name;
	private int price;
	private int orderCount;

	public PopularCoffeeMenuResponse(Long id, String name, int price, Integer orderCount) {
		this.menuId = id;
		this.name = name;
		this.price = price;
		this.orderCount = orderCount;
	}

	public static PopularCoffeeMenuResponse of(CoffeeMenu coffeeMenu, int orderCount) {
		return new PopularCoffeeMenuResponse(coffeeMenu.getId(), coffeeMenu.getName(), coffeeMenu.getPrice(), orderCount);
	}
}
