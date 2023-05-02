package com.dhlee.coffeepossystem.coffeeMenu.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.dhlee.coffeepossystem.coffeeMenu.dto.CoffeeMenuResponse;
import com.dhlee.coffeepossystem.coffeeMenu.dto.PopularCoffeeMenuResponse;
import com.dhlee.coffeepossystem.coffeeMenu.service.CoffeeMenuService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/coffee-menu")
public class CoffeeMenuController {
	private final CoffeeMenuService coffeeMenuService;

	@GetMapping("/list")
	public ResponseEntity<List<CoffeeMenuResponse>> getCoffeeMenuList() {
		return ResponseEntity.ok().body(coffeeMenuService.getCoffeeMenuList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CoffeeMenuResponse> getCoffeeMenu(@PathVariable Long id) {
		return ResponseEntity.ok().body(coffeeMenuService.getCoffeeMenu(id));
	}

	@GetMapping("/popular/list")
	public ResponseEntity<List<PopularCoffeeMenuResponse>> getPopularList() {
		return ResponseEntity.ok().body(coffeeMenuService.getPopularList());
	}
}
