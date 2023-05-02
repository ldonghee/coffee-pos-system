package com.dhlee.coffeepossystem.point.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;

import com.dhlee.coffeepossystem.common.domain.BaseEntity;
import com.dhlee.coffeepossystem.point.exception.InvalidPointException;
import com.dhlee.coffeepossystem.point.exception.NotEnoughPointException;

@Entity
@Getter
public class Point extends BaseEntity {
	private static final int MIN_POINT = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	private int point;

	public Point() {
	}

	public Point(Long id, User user, int point) {
		this.id = id;
		this.user = user;
		this.point = point;
	}

	public void addPoint(int point) {
		validate(point);
		this.point += point;
	}

	public void subtractPoint(int point) {
		validate(point);
		this.point -= point;
	}

	private void validate(int point) {
		if (point < MIN_POINT) {
			throw new InvalidPointException();
		}
	}

	public void pay(int price) {
		if (this.point < price) {
			throw new NotEnoughPointException();
		}
		subtractPoint(price);
	}
}
