package com.dhlee.coffeepossystem.coffeeMenu.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PopularCoffeeMenuTest {
	@Test
	@DisplayName("생성 테스트")
	public void create() {
		// given & when
		PopularCoffeeMenu popularCoffeeMenu = new PopularCoffeeMenu(1L, 3);

		// then
		assertThat(popularCoffeeMenu.getId()).isEqualTo(1L);
		assertThat(popularCoffeeMenu.getOrderCount()).isEqualTo(3);
	}
}
