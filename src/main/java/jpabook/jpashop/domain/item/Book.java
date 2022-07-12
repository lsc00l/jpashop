package jpabook.jpashop.domain.item;

import javax.persistence.*;

import lombok.*;

@Entity
@DiscriminatorValue(value = "B")
@Getter @Setter
public class Book extends Item {
	
	private String author;
	private String isbn;
}
