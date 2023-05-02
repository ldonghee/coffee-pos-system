package com.dhlee.coffeepossystem.coffeeMenu.exception;

public class CoffeeMenuNotFoundException extends RuntimeException {
	public CoffeeMenuNotFoundException() {
		super("사용자를 찾을 수 없습니다.");
	}
}
