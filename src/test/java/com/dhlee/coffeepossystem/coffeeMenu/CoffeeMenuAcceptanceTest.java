package com.dhlee.coffeepossystem.coffeeMenu;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.dhlee.coffeepossystem.coffeeMenu.dto.CoffeeMenuResponse;
import com.dhlee.coffeepossystem.common.AcceptanceTest;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class CoffeeMenuAcceptanceTest extends AcceptanceTest {
	private static final String ORDER_BASE_PATH = "/coffee-menu";

	@DisplayName("커피 메뉴 목록 조회한다")
	@Test
	void getCoffeeMenuList() {
		// given & when
		ExtractableResponse<Response> response = 커피_메뉴_목록_조회_요청();
		// then
		커피_메뉴_목록_조회_응답됨(response);
		커피_메뉴_목록_조회_확인됨(response);
	}

	@DisplayName("커피 메뉴 단건 조회한다")
	@Test
	void getCoffeeMenu() {
		// given & when
		ExtractableResponse<Response> response = 커피_메뉴_단건_조회_요청(1L);
		// then
		커피_메뉴_단건_조회_응답됨(response);
		커피_메뉴_단건_조회_확인됨(response);
	}

	public static ExtractableResponse<Response> 커피_메뉴_목록_조회_요청() {
		return RestAssured.given().log().all()
						  .contentType(MediaType.APPLICATION_JSON_VALUE)
						  .when()
						  .get(ORDER_BASE_PATH + "/list")
						  .then().log().all()
						  .extract();
	}

	public static void 커피_메뉴_목록_조회_응답됨(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	public static void 커피_메뉴_목록_조회_확인됨(ExtractableResponse<Response> response) {
		List<CoffeeMenuResponse> responseList = response.jsonPath().getObject(".", List.class);
		assertThat(responseList.size() > 0).isTrue();
	}

	public static ExtractableResponse<Response> 커피_메뉴_단건_조회_요청(Long id) {
		return RestAssured.given().log().all()
						  .contentType(MediaType.APPLICATION_JSON_VALUE)
						  .when()
						  .get(ORDER_BASE_PATH + "/" + id)
						  .then().log().all()
						  .extract();
	}

	public static void 커피_메뉴_단건_조회_응답됨(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	public static void 커피_메뉴_단건_조회_확인됨(ExtractableResponse<Response> response) {
		CoffeeMenuResponse coffeeMenuResponse = response.jsonPath().getObject(".", CoffeeMenuResponse.class);
		assertThat(coffeeMenuResponse.getId()).isEqualTo(1L);
	}
}
