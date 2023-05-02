package com.dhlee.coffeepossystem.point.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhlee.coffeepossystem.point.domain.Point;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
	Optional<Point> findByUserId(Long userId);
}
