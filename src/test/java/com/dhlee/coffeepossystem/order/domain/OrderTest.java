package com.dhlee.coffeepossystem.order.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dhlee.coffeepossystem.coffeeMenu.domain.CoffeeMenu;
import com.dhlee.coffeepossystem.point.domain.User;

public class OrderTest {
	private Order order;

	@BeforeEach
	public void setUp() {
		CoffeeMenu coffeeMenu = new CoffeeMenu(1L, "아메리카노", 3000);
		User user = new User(1L, "TEST");
		order = new Order(user);
		OrderDetail orderDetail = new OrderDetail(coffeeMenu, 1);
		orderDetail.setOrder(order);
		order.addOrderDetail(orderDetail);
	}

	@Test
	@DisplayName("주문 상세 추가 테스트")
	public void addOrderDetail() {
		// given & when
		CoffeeMenu coffeeMenu = new CoffeeMenu(2L, "콜드브루", 3000);
		OrderDetail orderDetail = new OrderDetail(coffeeMenu, 1);
		orderDetail.setOrder(order);
		order.addOrderDetail(orderDetail);

		// then
		assertThat(order.getOrderDetails().size()).isEqualTo(2);
	}

	@Test
	@DisplayName("주문 금액 테스트")
	public void getTotalPrice() {
		// given & when
		CoffeeMenu coffeeMenu = new CoffeeMenu(2L, "콜드브루", 3000);
		OrderDetail orderDetail = new OrderDetail(coffeeMenu, 3);
		orderDetail.setOrder(order);
		order.addOrderDetail(orderDetail);

		// then
		assertThat(order.getTotalPrice()).isEqualTo(12000);
	}
}
