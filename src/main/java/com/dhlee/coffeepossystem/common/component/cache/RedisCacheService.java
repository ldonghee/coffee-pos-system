package com.dhlee.coffeepossystem.common.component.cache;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.dhlee.coffeepossystem.common.config.cache.RedisCacheKey;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisCacheService implements CacheService {
	private final RedisTemplate<String, String> redisHashTemplate;

	@Override
	public void savePopularMenu(Long menuId) {
		HashOperations<String, String, Integer> hashOperations = redisHashTemplate.opsForHash();

		Map<String, Integer> entries = hashOperations.entries(RedisCacheKey.POPULAR_MENU.name());
		for (String key : entries.keySet()) {
			log.info("==================== Menu ID "+ key + ", Count : " + entries.get(key));
		}

		log.info("==================== Insert cache Menu ID "+ menuId);
		hashOperations.increment(RedisCacheKey.POPULAR_MENU.name(), String.valueOf(menuId), 1);
	}

	@Override
	public Map<String, Integer> getPopularMenuEntries() {
		HashOperations<String, String, Integer> hashOperations = redisHashTemplate.opsForHash();
		return hashOperations.entries(RedisCacheKey.POPULAR_MENU.name());
	}

	@Override
	public void deletePopularMenu() {
		redisHashTemplate.delete(RedisCacheKey.POPULAR_MENU.name());
	}
}
