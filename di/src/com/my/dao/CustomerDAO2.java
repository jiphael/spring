package com.my.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.DuplicatedException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Customer;

public interface CustomerDAO2 {
	void insert(Customer c) throws AddException, DuplicatedException;
	List<Customer> selectAll() throws FindException;
	Customer selectById(String id)throws FindException;
	List<Customer> selectByName(String word) throws FindException;
	void update(Customer c) throws ModifyException;
	void delete(String id) throws RemoveException;	
	
}
