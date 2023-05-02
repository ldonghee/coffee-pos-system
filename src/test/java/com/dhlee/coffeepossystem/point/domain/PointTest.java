package com.dhlee.coffeepossystem.point.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dhlee.coffeepossystem.point.exception.InvalidPointException;
import com.dhlee.coffeepossystem.point.exception.NotEnoughPointException;

public class PointTest {
	private final User user = new User(1L, "Test");
	private final Point point = new Point(1L, user, 10000);

	@Test
	@DisplayName("포인트 추가 테스트")
	public void addPoint() {
		// given & when
		point.addPoint(3000);
		// then
		assertThat(point.getPoint()).isEqualTo(13000);
	}

	@Test
	@DisplayName("포인트 추가 예외 테스트")
	public void addPoint_exception() {
		// given & when & then
		Assertions.assertThrows(InvalidPointException.class, () -> point.addPoint(-3000));
	}

	@Test
	@DisplayName("포인트 빼기 테스트")
	public void subtractPoint() {
		// given & when
		point.subtractPoint(3000);
		// then
		assertThat(point.getPoint()).isEqualTo(7000);
	}

	@Test
	@DisplayName("포인트 빼기 예외 테스트")
	public void subtractPoint_exception() {
		// given & when & then
		Assertions.assertThrows(InvalidPointException.class, () -> point.subtractPoint(-3000));
	}

	@Test
	@DisplayName("포인트 결재하기 테스트")
	public void pay() {
		// given & when
		point.pay(3000);
		// then
		assertThat(point.getPoint()).isEqualTo(7000);
	}

	@Test
	@DisplayName("포인트 결재하기 예외 테스트")
	public void pay_exception() {
		// given & when
		point.pay(3000);
		point.pay(3000);
		point.pay(3000);
		// then
		Assertions.assertThrows(NotEnoughPointException.class, () -> point.pay(3000));
	}
}
