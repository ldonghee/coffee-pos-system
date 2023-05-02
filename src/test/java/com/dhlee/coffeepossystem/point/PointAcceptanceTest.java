package com.dhlee.coffeepossystem.point;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.dhlee.coffeepossystem.common.AcceptanceTest;
import com.dhlee.coffeepossystem.point.dto.PointRequest;
import com.dhlee.coffeepossystem.point.dto.PointResponse;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class PointAcceptanceTest extends AcceptanceTest {
	private static final String POINT_BASE_PATH = "/point";

	@DisplayName("포인트를 충전한다")
	@Test
	void charge_point() {
		// given
		PointRequest pointRequest = new PointRequest(1L, 3000);
		// when
		ExtractableResponse<Response> response = 포인트_충전_요청(pointRequest);
		// then
		포인트_충전_응답됨(response);
		포인트_충전_확인됨(response, 23000);
	}

	public static ExtractableResponse<Response> 포인트_충전_요청(PointRequest pointRequest) {
		return RestAssured.given().log().all()
						  .body(pointRequest)
						  .contentType(MediaType.APPLICATION_JSON_VALUE)
						  .when()
						  .patch(POINT_BASE_PATH + "/charge")
						  .then().log().all()
						  .extract();
	}

	public static void 포인트_충전_응답됨(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	public static void 포인트_충전_확인됨(ExtractableResponse<Response> response, int expectPoint) {
		PointResponse pointResponse = response.jsonPath().getObject(".", PointResponse.class);
		assertThat(pointResponse.getPoint()).isEqualTo(expectPoint);
	}
}
