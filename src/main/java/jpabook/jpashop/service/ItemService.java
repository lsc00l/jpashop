package jpabook.jpashop.service;

import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import jpabook.jpashop.domain.item.*;
import jpabook.jpashop.repository.*;
import lombok.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
	
	private final itemRepository itemRepository;
	
	@Transactional
	public void saveItem(Item item) {
		itemRepository.saveItem(item);
	}
	
	/*
	 * 준영속 엔티티를 수정하는 방법 2
	 *  1. 변경 감지 기능 (dirty checking) - 아래 코드
	 *  2. 병합 사용 : merge() - 아래 코드를 JPA가 merge로 쉽게 만든것 
	 *  
	 * [2번 병합 사용 시 주의점]
	 * 병합은 변경 감지 처럼 원하는 속성만 바꾸지 않고 모든 속성을 바꾸기 때문에 
	 * 병합 시 값이 없으면 Null 이 들어갈 수 있음
	 * 
	 * -> 가급적 변경 감지로 사용하여 필요한 필드만 사용한다.
	 */
	@Transactional
	public Item updateItem(Long itemId, String name, int price, int stockQuantity) {
		Item findItem = itemRepository.findOne(itemId);
		findItem.setPrice(price);
		findItem.setName(name);
		findItem.setStockQuantity(stockQuantity);
		
		return findItem;
	}
	
	public List<Item> findItems(){
		return itemRepository.findAll();
	}
	
	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}

}
