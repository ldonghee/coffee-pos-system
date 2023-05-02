package com.dhlee.coffeepossystem.point.dto;

import lombok.Getter;

import com.dhlee.coffeepossystem.point.domain.Point;

@Getter
public class PointResponse {
	private Long userId;
	private int point;

	private PointResponse(Long userId, int point) {
		this.userId = userId;
		this.point = point;
	}

	public static PointResponse of(Point point) {
		return new PointResponse(point.getUser().getId(), point.getPoint());
	}
}
