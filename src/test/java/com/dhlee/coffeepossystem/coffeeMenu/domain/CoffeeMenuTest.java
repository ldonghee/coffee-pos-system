package com.dhlee.coffeepossystem.coffeeMenu.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CoffeeMenuTest {
	@Test
	@DisplayName("생성 테스트")
	public void create() {
		// given & when
		CoffeeMenu coffeeMenu = new CoffeeMenu(1L, "아메리카노", 3000);

		// then
		assertThat(coffeeMenu.getName()).isEqualTo("아메리카노");
		assertThat(coffeeMenu.getPrice()).isEqualTo(3000);
	}
}
