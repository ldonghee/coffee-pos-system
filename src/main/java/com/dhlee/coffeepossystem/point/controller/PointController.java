package com.dhlee.coffeepossystem.point.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.dhlee.coffeepossystem.point.dto.PointRequest;
import com.dhlee.coffeepossystem.point.dto.PointResponse;
import com.dhlee.coffeepossystem.point.service.PointService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointController {
	private final PointService pointService;

	@PatchMapping("/charge")
	public ResponseEntity<PointResponse> charge(@RequestBody PointRequest pointRequest) {
		PointResponse pointResponse = pointService.updatePoint(pointRequest);
		return ResponseEntity.ok().body(pointResponse);
	}
}
