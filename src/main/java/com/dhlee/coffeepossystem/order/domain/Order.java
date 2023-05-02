package com.dhlee.coffeepossystem.order.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import lombok.Getter;

import com.dhlee.coffeepossystem.coffeeMenu.domain.CoffeeMenu;
import com.dhlee.coffeepossystem.common.domain.BaseEntity;
import com.dhlee.coffeepossystem.point.domain.User;

@Entity
@Table(name = "orders")
@Getter
public class Order extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails = new ArrayList<>();

	public Order() {
	}

	public Order(User user) {
		this.user = user;
	}

	public void addOrderDetail(OrderDetail orderDetail) {
		this.orderDetails.add(orderDetail);
	}

	public int getTotalPrice() {
		return orderDetails.stream()
						   .mapToInt(OrderDetail::getPrice)
						   .sum();
	}

	public List<CoffeeMenu> getCoffees() {
		return orderDetails.stream()
				.map(OrderDetail::getCoffee)
				.collect(Collectors.toList());
	}
}
