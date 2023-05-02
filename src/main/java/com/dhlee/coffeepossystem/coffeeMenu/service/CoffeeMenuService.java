package com.dhlee.coffeepossystem.coffeeMenu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.dhlee.coffeepossystem.coffeeMenu.domain.CoffeeMenu;
import com.dhlee.coffeepossystem.coffeeMenu.domain.PopularCoffeeMenu;
import com.dhlee.coffeepossystem.coffeeMenu.dto.CoffeeMenuResponse;
import com.dhlee.coffeepossystem.coffeeMenu.dto.PopularCoffeeMenuResponse;
import com.dhlee.coffeepossystem.coffeeMenu.exception.CoffeeMenuNotFoundException;
import com.dhlee.coffeepossystem.coffeeMenu.repository.CoffeeMenuRepository;
import com.dhlee.coffeepossystem.coffeeMenu.repository.PopularCoffeeMenuRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CoffeeMenuService {
	private final CoffeeMenuRepository coffeeMenuRepository;
	private final PopularCoffeeMenuRepository popularCoffeeMenuRepository;

	@Transactional(readOnly = true)
	@Cacheable(value="MENU_LIST")
	public List<CoffeeMenuResponse> getCoffeeMenuList() {
		List<CoffeeMenu> coffeeMenuList = coffeeMenuRepository.findAll();
		return coffeeMenuList.stream()
				.map(CoffeeMenuResponse::of)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CoffeeMenu findById(Long id) {
		return coffeeMenuRepository.findById(id).orElseThrow(CoffeeMenuNotFoundException::new);
	}

	@Transactional(readOnly = true)
	public CoffeeMenuResponse getCoffeeMenu(Long id) {
		return CoffeeMenuResponse.of(findById(id));
	}

	@Transactional(readOnly = true)
	public List<PopularCoffeeMenuResponse> getPopularList() {
		List<PopularCoffeeMenu> popularCoffeeMenuList = popularCoffeeMenuRepository.findTop3ByOrderByOrderCountDesc();

		return popularCoffeeMenuList.stream()
									.map(menu -> {
										CoffeeMenu coffeeMenu = findById(menu.getId());
										return PopularCoffeeMenuResponse.of(coffeeMenu, menu.getOrderCount());
									}).collect(Collectors.toList());
	}
}
