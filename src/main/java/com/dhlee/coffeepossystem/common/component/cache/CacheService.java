package com.dhlee.coffeepossystem.common.component.cache;

import java.util.Map;

public interface CacheService {
	void deletePopularMenu();
	void savePopularMenu(Long menuId);
	Map<String, Integer> getPopularMenuEntries();
}
