package jpabook.jpashop.repository;

import javax.persistence.*;

import org.springframework.stereotype.*;

import jpabook.jpashop.domain.*;
import lombok.*;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
	
	private final EntityManager em;
	
	
	public void save(Order order) {
		em.persist(order);
	}
	
	public Order findOne(Long id) {
		return em.find(Order.class, id);
	}
	
//	public List<Order> findAll(OrderSearch orderSearch){}

}
