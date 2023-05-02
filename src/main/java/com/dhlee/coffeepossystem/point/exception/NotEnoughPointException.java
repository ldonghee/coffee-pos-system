package com.dhlee.coffeepossystem.point.exception;

public class NotEnoughPointException extends RuntimeException {
	public NotEnoughPointException() {
		super("포인트가 부족합니다.");
	}
}
