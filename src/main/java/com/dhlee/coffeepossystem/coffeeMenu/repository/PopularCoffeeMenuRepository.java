package com.dhlee.coffeepossystem.coffeeMenu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhlee.coffeepossystem.coffeeMenu.domain.PopularCoffeeMenu;

@Repository
public interface PopularCoffeeMenuRepository extends JpaRepository<PopularCoffeeMenu, Long> {
	List<PopularCoffeeMenu> findTop3ByOrderByOrderCountDesc();
}
