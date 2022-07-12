package jpabook.jpashop.domain;

import java.util.*;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter @Setter
public class Member {
	
	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;
	
	private String name;
	
	@Embedded
	private Address address;
	
	//mappedBy : 난 Order에 있는 member 필드에 의해서 매핑된거야 
	// => Member.orders와 Order.member 즁에 Order.member가 주인
	@OneToMany(mappedBy = "member")  
	private List<Order> orders = new ArrayList<>();
}
