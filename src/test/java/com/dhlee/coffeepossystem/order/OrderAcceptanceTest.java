package com.dhlee.coffeepossystem.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.dhlee.coffeepossystem.common.AcceptanceTest;
import com.dhlee.coffeepossystem.order.dto.OrderRequest;
import com.dhlee.coffeepossystem.order.dto.OrderResponse;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class OrderAcceptanceTest extends AcceptanceTest {
	private static final String ORDER_BASE_PATH = "/order";

	@DisplayName("주문/결재 한다")
	@Test
	void orderAndPay() {
		// given
		OrderRequest orderRequest = new OrderRequest(1L, 1L);
		// when
		ExtractableResponse<Response> response = 주문_결재_요청(orderRequest);
		// then
		주문_결재_요청_응답됨(response);
		주문_결재_요청_확인됨(response);
	}

	public static ExtractableResponse<Response> 주문_결재_요청(OrderRequest orderRequest) {
		return RestAssured.given().log().all()
						  .body(orderRequest)
						  .contentType(MediaType.APPLICATION_JSON_VALUE)
						  .when()
						  .post(ORDER_BASE_PATH)
						  .then().log().all()
						  .extract();
	}

	public static void 주문_결재_요청_응답됨(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	public static void 주문_결재_요청_확인됨(ExtractableResponse<Response> response) {
		OrderResponse orderResponse = response.jsonPath().getObject(".", OrderResponse.class);

		assertThat(orderResponse.getMenuId()).isEqualTo(1L);
		assertThat(orderResponse.getUserId()).isEqualTo(1L);
	}
}
