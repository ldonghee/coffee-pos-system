package com.dhlee.coffeepossystem.coffeeMenu;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.dhlee.coffeepossystem.coffeeMenu.dto.PopularCoffeeMenuResponse;
import com.dhlee.coffeepossystem.common.AcceptanceTest;
import com.dhlee.coffeepossystem.order.OrderAcceptanceTest;
import com.dhlee.coffeepossystem.order.dto.OrderRequest;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class PopularCoffeeMenuAcceptanceTest extends AcceptanceTest {
	private static final String ORDER_BASE_PATH = "/coffee-menu";

	@DisplayName("인기 메뉴 TOP3 목록 조회한다")
	@Test
	void getPopularList() throws InterruptedException {
		// given
		OrderAcceptanceTest.주문_결재_요청(new OrderRequest(1L, 1L));
		OrderAcceptanceTest.주문_결재_요청(new OrderRequest(1L, 1L));
		OrderAcceptanceTest.주문_결재_요청(new OrderRequest(1L, 1L));
		OrderAcceptanceTest.주문_결재_요청(new OrderRequest(2L, 2L));
		OrderAcceptanceTest.주문_결재_요청(new OrderRequest(2L, 2L));
		OrderAcceptanceTest.주문_결재_요청(new OrderRequest(3L, 3L));
		OrderAcceptanceTest.주문_결재_요청(new OrderRequest(3L, 3L));
		OrderAcceptanceTest.주문_결재_요청(new OrderRequest(3L, 4L));

		Thread.sleep(10000);

		// when
		ExtractableResponse<Response> response = 인기_메뉴_TOP3_목록_조회_요청();

		// then
		인기_메뉴_TOP3_목록_조회_응답됨(response);
		인기_메뉴_TOP3_목록_조회_확인됨(response);
	}

	public static ExtractableResponse<Response> 인기_메뉴_TOP3_목록_조회_요청() {
		return RestAssured.given().log().all()
						  .contentType(MediaType.APPLICATION_JSON_VALUE)
						  .when()
						  .get(ORDER_BASE_PATH + "/popular/list")
						  .then().log().all()
						  .extract();
	}

	public static void 인기_메뉴_TOP3_목록_조회_응답됨(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	public static void 인기_메뉴_TOP3_목록_조회_확인됨(ExtractableResponse<Response> response) {
		List<PopularCoffeeMenuResponse> responseList = response.jsonPath().getList(".", PopularCoffeeMenuResponse.class);

		assertThat(responseList.size() > 0).isTrue();
		PopularCoffeeMenuResponse top1_popular_menu = responseList.stream().filter(r -> r.getMenuId() == 1L).findFirst().get();
		PopularCoffeeMenuResponse top2_popular_menu = responseList.stream().filter(r -> r.getMenuId() == 2L).findFirst().get();
		PopularCoffeeMenuResponse top3_popular_menu = responseList.stream().filter(r -> r.getMenuId() == 3L).findFirst().get();
		assertThat(top1_popular_menu.getOrderCount()).isEqualTo(3);
		assertThat(top2_popular_menu.getOrderCount()).isEqualTo(2);
		assertThat(top3_popular_menu.getOrderCount()).isEqualTo(2);
	}
}
