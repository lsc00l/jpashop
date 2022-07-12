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
		itemRepository.save(item);
	}
	
	public List<Item> findItems(){
		return itemRepository.findAll();
	}
	
	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}

}
