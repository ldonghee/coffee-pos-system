package com.dhlee.coffeepossystem.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import com.dhlee.coffeepossystem.common.component.CacheService;

@SpringBootTest
public class CacheRedisServiceTest {
	private static final int THREAD_COUNT = 1000;
	private static final ExecutorService service = Executors.newFixedThreadPool(THREAD_COUNT);

	@Autowired
	private RedisTemplate redisHashTemplate;

	@Autowired
	private CacheService cacheService;

	@Test
	@DisplayName("100번의 동시 주문하기 시, 횟수 증가 테스트")
	public void concurrency() throws InterruptedException, BrokenBarrierException {
		// given
		CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_COUNT + 1);

		// when
		CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
		for (int i=0; i < THREAD_COUNT; i++) {
			service.execute(() -> {
				try {
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
				cacheService.savePopularMenu(1L);
				latch.countDown();
			});
		}

		cyclicBarrier.await();
		latch.await();

		// then
		Map<String, Integer> popularMenuEntries = cacheService.getPopularMenuEntries();
		String count = String.valueOf(popularMenuEntries.get("1"));
		assertThat(Integer.valueOf(count)).isEqualTo(THREAD_COUNT);
	}
}
