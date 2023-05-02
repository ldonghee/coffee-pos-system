package com.dhlee.coffeepossystem.coffeeMenu.component;

import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.dhlee.coffeepossystem.coffeeMenu.domain.PopularCoffeeMenu;
import com.dhlee.coffeepossystem.coffeeMenu.repository.PopularCoffeeMenuRepository;
import com.dhlee.coffeepossystem.common.component.cache.CacheService;

@Slf4j
@Component
@RequiredArgsConstructor
public class PopularCoffeeMenuSchedule {
	private final CacheService cacheService;
	private final PopularCoffeeMenuRepository popularCoffeeMenuRepository;

	@Scheduled(cron = "*/5 * * * * *")
	public void RedisToDatabaseTask() {
		Map<String, Integer> entries = cacheService.getPopularMenuEntries();

		log.info("Schedule Popular Menu Save Start");
		for (String key : entries.keySet()) {
			savePopularMenu(key, String.valueOf(entries.get(key)));
		}
	}

	@Transactional
	void savePopularMenu(String key, String count) {
		log.info("BEFORE Menu Id : " + key + ", Count : " + count);
		PopularCoffeeMenu save = popularCoffeeMenuRepository.save(new PopularCoffeeMenu(Long.valueOf(key), Integer.valueOf(count)));
		log.info("AFTER Menu Id : " + save.getId() + ", Count : " + save.getOrderCount());
	}

//	@Scheduled(cron = "* * 7 * * *")
	@Scheduled(cron = "0 */5 * * * *")
	public void RemoveRedisWithDatabaseTask() {
		log.info("Schedule Popular Menu Delete Start");
		popularCoffeeMenuRepository.deleteAll();
		cacheService.deletePopularMenu();
	}
}
