package com.dhlee.coffeepossystem.point.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

import com.dhlee.coffeepossystem.common.domain.BaseEntity;

@Entity
@Table(name = "users")
@Getter
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	private String name;

	public User() {
	}

	public User(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
