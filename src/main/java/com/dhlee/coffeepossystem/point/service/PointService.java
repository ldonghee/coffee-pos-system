package com.dhlee.coffeepossystem.point.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.dhlee.coffeepossystem.point.domain.Point;
import com.dhlee.coffeepossystem.point.dto.PointRequest;
import com.dhlee.coffeepossystem.point.dto.PointResponse;
import com.dhlee.coffeepossystem.point.exception.UserNotFoundException;
import com.dhlee.coffeepossystem.point.repository.PointRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PointService {
	private final PointRepository pointRepository;

	public PointResponse updatePoint(PointRequest pointRequest) {
		Point point = findPointByUserId(pointRequest.getUserId());
		point.addPoint(pointRequest.getPoint());
		return PointResponse.of(point);
	}

	@Transactional(readOnly = true)
	public Point findPointByUserId(Long userId) {
		return pointRepository.findByUserId(userId).orElseThrow(UserNotFoundException::new);
	}
}
