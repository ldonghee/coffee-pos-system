package com.dhlee.coffeepossystem.coffeeMenu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

import com.dhlee.coffeepossystem.common.domain.BaseEntity;

@Entity
@Getter
public class CoffeeMenu extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coffee_menu_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int price;

	public CoffeeMenu() {
	}

	public CoffeeMenu(Long id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
}
