package com.my.vo;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
public class Person
             implements Serializable{	
	private static final long serialVersionUID = 1L;
	//private String name;
	@NonNull
	protected String name;
	@Autowired(required = false)
	private Postal postal;//���θ� �����ȣ ����
	
	@NonNull
	private String addr; //���ּ�
//	public Person() {
//		
//	}
//	public Person(String name, String addr) { 
//		this(name, null, addr);
//	}	
//	public Person(String name, Postal postal, String addr) {
//		this.name = name;
//		this.setPostal(postal);
//		this.addr = addr;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getAddr() {
//		return addr;
//	}
//	public void setAddr(String addr) {
//		this.addr = addr;
//	}
//	public void printInfo() {
//		System.out.println(name + ":"+ addr);
//	}
//	public Postal getPostal() {
//		return postal;
//	}
//	public void setPostal(Postal postal) {
//		this.postal = postal;
//	}
	
}
