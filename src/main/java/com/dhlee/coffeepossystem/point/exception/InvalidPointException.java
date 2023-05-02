package com.dhlee.coffeepossystem.point.exception;

public class InvalidPointException extends RuntimeException {
	public InvalidPointException() {
		super("유효하지 않은 충전 금액입니다.");
	}
}
