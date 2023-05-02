package com.dhlee.coffeepossystem.point.dto;

import lombok.Getter;

@Getter
public class PointRequest {
	private Long userId;
	private int point;

	public PointRequest(Long userId, int point) {
		this.userId = userId;
		this.point = point;
	}
}
