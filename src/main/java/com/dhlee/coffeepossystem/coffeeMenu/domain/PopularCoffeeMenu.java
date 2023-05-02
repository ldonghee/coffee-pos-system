package com.dhlee.coffeepossystem.coffeeMenu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class PopularCoffeeMenu {
	@Id
	@Column(name = "coffee_menu_id")
	private Long id;

	@Column(nullable = false)
	private int orderCount;

	public PopularCoffeeMenu() {
	}

	public PopularCoffeeMenu(Long id, Integer orderCount) {
		this.id = id;
		this.orderCount = orderCount;
	}
}
