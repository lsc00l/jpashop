package jpabook.jpashop.domain;

import java.time.*;
import java.util.*;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
	
	@Id @GeneratedValue
	@Column(name = "order_id")
	private Long id;
	
	//XxxToOne 의 기본 연관관계 타입이 EAGER 이기 때문에 LAZY를 꼭 써준다.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	//CascadeType.ALL : Order 를 persist하면 해당 변수(List<OrderItem>)도 persist 한다. 
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;
	
	private LocalDateTime orderDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus staus; // 주문상태 [ORDER, CANCEL]
	
	
	//== 연관관계 메서드 ==//
	public void setMember(Member member) {
		this.member = member;
		member.getOrders().add(this);
	}
	
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
	
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}
	
	//== 생성 메서드 ==//
	public static Order createORder(Member member, Delivery delivery, OrderItem... orderItems) {
		Order order = new Order();
		order.setMember(member);
		order.setDelivery(delivery);
		
		for(OrderItem orderItem : orderItems) {
			order.addOrderItem(orderItem);
		}
		order.setStaus(OrderStatus.ORDER);
		order.setOrderDate(LocalDateTime.now());
		
		return  order;
	}
	
	//== 비즈니스 로직 ==//
	/**
	 * 주문 취소
	 */
	public void cancel() {
		if(delivery.getStatus() == DeliveryStatus.COMP)
			throw new IllegalStateException("이미 배송된 상품은 취소가 불가능합니다.");
		
		this.setStaus(OrderStatus.CANCEL);
		for(OrderItem orderItem : orderItems) {
			orderItem.cancel();
		}
	}
	
	
	//== 조회 로직 ==//
	/**
	 * 전체 주문 가격 조회
	 * @return
	 */
	public int getTotalPrice() {
		return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
		
		/*int totalPrice = 0;		
		for(OrderItem orderItem : orderItems) {
			totalPrice += orderItem.getTotalPrice();
		}
		return totalPrice;*/
	}
	
	
	
}