package jpabook.jpashop.domain.item;

import javax.persistence.*;

import lombok.*;

@Entity
@DiscriminatorValue(value = "M")
@Getter @Setter
public class Movie extends Item {
	
	private String director;
	private String actor;

}
