package jpabook.jpashop.domain.item;

import javax.persistence.*;

import lombok.*;

@Entity
@DiscriminatorValue(value = "A")
@Getter @Setter
public class Album {

	@Id @GeneratedValue
	@Column(name = "category_id")
	private Long id;
	
	
}
