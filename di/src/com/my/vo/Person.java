package com.my.vo;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Setter
@Getter
public class Person
             implements Serializable{	
	private static final long serialVersionUID = 1L;
//	@NonNull
	protected String name;
	
	@Autowired(required = false)	
//eclipse오류발생시 우클릭->프로젝트->Maven->Update Project
//	@Setter(onMethod_={@Autowired(required = false)})
	private Postal postal;
	
//	@NonNull
	private String addr; 
//	public Person() {}
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
