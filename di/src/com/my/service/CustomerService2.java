package com.my.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.my.dao.CustomerDAO2;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.share.CustomerShare;
import com.my.vo.Customer;

@Service(value = "customerService")
public class CustomerService2 {
	@Autowired
	@Qualifier(value = "customerDAOOracle")
	private CustomerDAO2 dao;
	
	public CustomerDAO2 getDao() {
		return dao;
	}
	public void setDao(CustomerDAO2 dao) {
		this.dao = dao;
	}
	
	public void add(Customer c) throws AddException{
		dao.insert(c);
	}
	public List<Customer> findAll() throws FindException{
		return dao.selectAll();
	}
	public Customer findById(String id) throws FindException{ 
		return dao.selectById(id); 
	}
	public List<Customer> findByName(String word) throws FindException{ 
		return dao.selectByName(word);
	}
	public void login(String id, String pwd) throws FindException{
		Customer c; 
		try {
			c = dao.selectById(id);
			CustomerShare.loginedCustomer = c; //로그인된 고객객체를 공유
		}catch(FindException e) {
			throw new FindException("로그인 실패");
		}
		
		if(!c.getPwd().equals(pwd)) {
			throw new FindException("로그인 실패");
		}
	}	
	public void modify(Customer c) throws ModifyException {
		dao.update(c);
	}
	public void remove(String id) throws RemoveException {
		dao.delete(id);
	}
}
