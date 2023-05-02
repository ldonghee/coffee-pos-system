package com.dhlee.coffeepossystem.common.component;

import java.util.Map;

public interface CacheService {
	void deletePopularMenu();
	void savePopularMenu(Long menuId);
	Map<String, Integer> getPopularMenuEntries();
}
