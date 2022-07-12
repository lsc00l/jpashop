package jpabook.jpashop.repository;

import java.util.*;

import javax.persistence.*;

import org.springframework.stereotype.*;

import jpabook.jpashop.domain.item.*;
import lombok.*;

@Repository
@RequiredArgsConstructor
public class itemRepository {
	
	private final EntityManager em;
	
	
	public void save(Item item) {
		if(item.getId() == null) {
			em.persist(item);
		}else {
			em.merge(item);//merge : update와 비슷한 개념
		}
	}
	
	
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}

	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class)
				.getResultList();
				
	}

}
