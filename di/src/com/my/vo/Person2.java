package com.my.vo;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

public class Person2
             implements Serializable{	
	private static final long serialVersionUID = 1L;
	//private String name;
	protected String name;
	@Autowired(required = false)
	private Postal postal;
	private String addr; 
	public Person2() {}
	public Person2(String name, String addr) { 
		this(name, null, addr);
	}	
	public Person2(String name, Postal postal, String addr) {
		this.name = name;
		this.setPostal(postal);
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public void printInfo() {
		System.out.println(name + ":"+ addr);
	}
	public Postal getPostal() {
		return postal;
	}
	public void setPostal(Postal postal) {
		this.postal = postal;
	}
	
}
