package com.dhlee.coffeepossystem.order.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

import com.dhlee.coffeepossystem.coffeeMenu.domain.CoffeeMenu;
import com.dhlee.coffeepossystem.common.domain.BaseEntity;

@Entity
@Getter
public class OrderDetail extends BaseEntity {
	private static final int MIN_QUANTITY = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coffee_menu_id")
	private CoffeeMenu coffee;

	private int quantity;
	private int price;


	public OrderDetail() {
	}

	public OrderDetail(CoffeeMenu coffeeMenu) {
		this(coffeeMenu, MIN_QUANTITY);
	}

	public OrderDetail(CoffeeMenu coffeeMenu, int quantity) {
		this.coffee = coffeeMenu;
		this.quantity = quantity;
		this.price = coffeeMenu.getPrice() * quantity;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
