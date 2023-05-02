package com.dhlee.coffeepossystem.coffeeMenu.dto;

import lombok.Getter;

import com.dhlee.coffeepossystem.coffeeMenu.domain.CoffeeMenu;

@Getter
public class CoffeeMenuResponse {
	private Long id;
	private String name;
	private int price;

	public CoffeeMenuResponse() {
	}

	public CoffeeMenuResponse(Long id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public static CoffeeMenuResponse of(CoffeeMenu coffeeMenu) {
		return new CoffeeMenuResponse(coffeeMenu.getId(), coffeeMenu.getName(), coffeeMenu.getPrice());
	}
}
