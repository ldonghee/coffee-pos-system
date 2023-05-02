package com.dhlee.coffeepossystem.coffeeMenu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhlee.coffeepossystem.coffeeMenu.domain.CoffeeMenu;

@Repository
public interface CoffeeMenuRepository extends JpaRepository<CoffeeMenu, Long> {
}
