package jpabook.jpashop.domain;

import javax.persistence.*;

import lombok.*;

@Embeddable
@Getter
public class Address {
	
	private String city;
	private String street;
	private String zipcode;
	
	


	protected Address() {
		
	}	
	public Address(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}


}