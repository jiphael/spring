package com.my.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.my.exception.AddException;
import com.my.exception.DuplicatedException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Customer;

@Repository
@Qualifier(value = "customerDAOMySQL")
public class CustomerDAOMySQL implements CustomerDAO2 {
	@Autowired
	private DataSource ds;
	@Override
	public void insert(Customer c) throws AddException, DuplicatedException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Customer> selectAll() throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer selectById(String id) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> selectByName(String word) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Customer c) throws ModifyException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) throws RemoveException {
		// TODO Auto-generated method stub

	}

}
