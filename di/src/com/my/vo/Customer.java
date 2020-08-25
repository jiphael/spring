package com.my.vo;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Component(value = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(of= {"id"}, callSuper = false)
public class Customer 
              extends Person {	
	private static final long serialVersionUID = 1L;
	
	private String id;
	transient private String pwd;
	public Customer(String id
			, String pwd
			, String name
			, String addr) {
//		super(name, addr);
		this.id = id;
		this.pwd = pwd;
		this.setName(name);
		this.setAddr(addr);
	}
	public Customer(String id
			, String pwd
			, String name
			, Postal postal
			, String addr) {
		this.id = id;
		this.pwd = pwd;
		this.setName(name);
		this.setPostal(postal);
		this.setAddr(addr);
	}
}