package com.dhlee.coffeepossystem.order.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dhlee.coffeepossystem.coffeeMenu.domain.CoffeeMenu;
import com.dhlee.coffeepossystem.point.domain.User;

public class OrderDetailTest {
	@Test
	@DisplayName("생성 테스트")
	public void create() {
		// given & when
		CoffeeMenu coffeeMenu = new CoffeeMenu(1L, "아메리카노", 3000);
		User user = new User(1L, "TEST");
		Order order = new Order(user);
		OrderDetail orderDetail = new OrderDetail(coffeeMenu, 1);
		orderDetail.setOrder(order);
		order.addOrderDetail(orderDetail);

		// then
		assertThat(orderDetail.getCoffee()).isEqualTo(coffeeMenu);
		assertThat(orderDetail.getOrder()).isEqualTo(order);
		assertThat(orderDetail.getQuantity()).isEqualTo(1);
	}
}
