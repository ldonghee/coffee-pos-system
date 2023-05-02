package com.dhlee.coffeepossystem.point.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {
	@Test
	@DisplayName("생성 테스트")
	public void create() {
		// given & when
		User user = new User(1L, "Test");
		// then
		assertThat(user.getId()).isEqualTo(1L);
		assertThat(user.getName()).isEqualTo("Test");
	}
}
